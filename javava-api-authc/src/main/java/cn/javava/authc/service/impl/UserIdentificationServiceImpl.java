package cn.javava.authc.service.impl;

import cn.javava.authc.service.QrcodeTokenService;
import cn.javava.authc.service.UserIdentificationService;
import cn.javava.authc.vo.AccessToken;
import cn.javava.authc.vo.ApiConfig;
import cn.javava.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Component
public class UserIdentificationServiceImpl implements UserIdentificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private QrcodeTokenService qrcodeTokenService;

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
            qrcodeTokenService.scanQrcodeSuccess(state, userVo);
        }
        return new AsyncResult<>("");
    }
}
