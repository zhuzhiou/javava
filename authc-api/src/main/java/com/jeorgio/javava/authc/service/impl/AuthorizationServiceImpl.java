package com.jeorgio.javava.authc.service.impl;

import com.jeorgio.javava.authc.service.AuthorizationService;
import com.jeorgio.javava.authc.vo.AccessToken;
import com.jeorgio.javava.authc.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Component
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Future<String> registerIfNecessary(String code, String state) {
        AccessToken accessToken = restTemplate.getForObject(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code",
                AccessToken.class,
                "wx3c9fe7990504ee43",
                "86a03cf6061df50185eb49dfaf31cc59",
                code);
        if (accessToken.getErrcode() == null) {
            UserInfo userInfo = restTemplate.getForObject(
                    "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}&lang=zh_CN",
                    UserInfo.class,
                    accessToken.getAccessToken(),
                    accessToken.getOpenid());
            System.out.println(userInfo.getOpenid());
            System.out.println(userInfo.getHeadimgurl());
        }
        return new AsyncResult<>("");
    }
}
