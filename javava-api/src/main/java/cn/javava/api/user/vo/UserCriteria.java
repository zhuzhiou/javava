package cn.javava.api.user.vo;

import java.io.Serializable;

@lombok.Data
public class UserCriteria implements Serializable {

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;
}
