package com.jeorgio.javava.authc.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.authc.service.QrcodeTokenService;
import com.jeorgio.javava.authc.vo.ApiConfig;
import com.jeorgio.javava.authc.vo.QrcodeToken;
import com.jeorgio.javava.users.service.UserService;
import com.jeorgio.javava.users.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.join;

@Service
public class QrcodeTokenServiceImpl implements QrcodeTokenService {

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${weixin.authz.redirect_uri}")
    private String redirect_uri;

    @Reference(version = "1.0", check = false)
    private UserService userService;

    @Override
    public QrcodeToken generateQrcodeToken() {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        String state;
        do {
            state = join(randomAlphabetic(2) + randomAlphanumeric(30));
        } while (!valueOps.setIfAbsent(state, ""));
        stringRedisTemplate.expire(state,5, TimeUnit.MINUTES);
        String qrcode = join("https://open.weixin.qq.com/connect/oauth2/authorize",
                "?appid=", apiConfig.getAppId(),
                "&redirect_uri=", UriUtils.encode(redirect_uri, StandardCharsets.UTF_8),
                "&response_type=code&scope=snsapi_userinfo",
                "&state=", state, "#wechat_redirect");
        QrcodeToken vo = new QrcodeToken();
        vo.setState(state);
        vo.setQrcode(qrcode);
        return vo;
    }

    public void scanQrcodeSuccess(String qrcode, UserVo userVo) {
        userService.save(userVo);
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(qrcode, userVo.getOpenid(), 1, TimeUnit.HOURS);
    }

    public String obtainPrincipal(String qrcode) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        return valueOps.get(qrcode);
    }
}
