package cn.javava.weixin.oauth2;

import java.io.Serializable;
import java.util.List;

@lombok.Data
public class UserInfo implements Serializable {

    private String openid;

    private String nickname;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private List<String> privilege;

    private String unionid;
}
