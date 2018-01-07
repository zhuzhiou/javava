package com.jeorgio.javava.authc.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.authc.service.TicketService;
import com.jeorgio.javava.authc.service.UserIdentificationService;
import com.jeorgio.javava.authc.vo.AccessToken;
import com.jeorgio.javava.authc.vo.ApiConfig;
import com.jeorgio.javava.users.service.UserService;
import com.jeorgio.javava.users.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Component
public class UserIdentificationServiceImpl implements UserIdentificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Reference(version = "1.0", check = false)
    private UserService userService;

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private TicketService ticketService;

    @Override
    public Future<String> identify(String code, String state) {
        AccessToken accessToken = restTemplate.getForObject(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code",
                AccessToken.class,
                apiConfig.getAppId(),
                apiConfig.getAppSecret(),
                code);
        if (accessToken.getErrcode() == null) {
            UserVo userVo = restTemplate.getForObject(
                    "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}&lang=zh_CN",
                    UserVo.class,
                    accessToken.getAccessToken(),
                    accessToken.getOpenid());
            userService.save(userVo);
            ticketService.updateTicket(code, userVo.getOpenid());
        }
        return new AsyncResult<>("");
    }
}
