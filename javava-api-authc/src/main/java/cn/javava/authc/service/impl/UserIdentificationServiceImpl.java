package cn.javava.authc.service.impl;

import cn.javava.authc.dao.UserDao;
import cn.javava.authc.service.UserIdentificationService;
import cn.javava.authc.vo.AccessToken;
import cn.javava.authc.vo.ApiConfig;
import cn.javava.authc.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserIdentificationServiceImpl implements UserIdentificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private UserDao userDao;

    @Override
    public UserVo identify(String code, String state) {
        AccessToken accessToken = restTemplate.getForObject(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code",
                AccessToken.class,
                apiConfig.getAppId(),
                apiConfig.getAppSecret(),
                code);
        UserVo userVo = null;
        if (accessToken.getErrcode() == null) {
            userVo = restTemplate.getForObject(
                    "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}&lang=zh_CN",
                    UserVo.class,
                    accessToken.getAccessToken(),
                    accessToken.getOpenid());
            userDao.save(userVo);
        }
        return userVo;
    }
}
