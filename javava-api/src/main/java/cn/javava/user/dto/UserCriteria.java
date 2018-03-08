package cn.javava.user.dto;

import java.io.Serializable;

@lombok.Data
public class UserCriteria implements Serializable {

    private String nickname;

    private String sex;

    private String country;

    private String province;

    private String city;
}