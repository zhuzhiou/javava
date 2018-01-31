package cn.javava.user.vo;

import java.io.Serializable;

@lombok.Data
public class UserCriteria implements Serializable {

    private String nicknameLike;

    private String sexEq;

    private String countryEq;

    private String countryLike;

    private String provinceLike;

    private String cityLike;
}
