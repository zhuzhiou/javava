package com.jeorgio.javava.users.vo;

import java.io.Serializable;

@lombok.Data
public class UserVo implements Serializable {

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String[] privilege;

    private String unionid;
}
