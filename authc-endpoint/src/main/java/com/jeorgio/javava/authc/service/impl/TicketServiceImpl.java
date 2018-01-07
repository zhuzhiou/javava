package com.jeorgio.javava.authc.service.impl;

import com.jeorgio.javava.authc.service.TicketService;
import com.jeorgio.javava.authc.vo.ApiConfig;
import com.jeorgio.javava.authc.vo.QrcodeLoginVo;
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
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${weixin.authz.redirect_uri}")
    private String redirect_uri;

    @Override
    public QrcodeLoginVo createTicket() {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        String ticket;
        do {
            ticket = join(randomAlphabetic(2) + randomAlphanumeric(30));
        } while (!valueOps.setIfAbsent(ticket, ""));
        stringRedisTemplate.expire(ticket,5, TimeUnit.MINUTES);
        String qrcode = join("https://open.weixin.qq.com/connect/oauth2/authorize",
                "?appid=", apiConfig.getAppId(),
                "&redirect_uri=", UriUtils.encode(redirect_uri, StandardCharsets.UTF_8),
                "&response_type=code&scope=snsapi_userinfo",
                "&state=", ticket, "#wechat_redirect");
        QrcodeLoginVo vo = new QrcodeLoginVo();
        vo.setTicket(ticket);
        vo.setQrcode(qrcode);
        return vo;
    }

    public void updateTicket(String ticket, String subject) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(ticket, subject, 1, TimeUnit.HOURS);
    }

    public String getTicket(String ticket) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        return valueOps.get(ticket);
    }
}
