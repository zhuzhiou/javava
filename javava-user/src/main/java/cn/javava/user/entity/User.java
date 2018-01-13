package cn.javava.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "jww_user")
@Entity
@lombok.Data
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "OPENID")
    private String openid;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "HEADIMGURL")
    private String headimgurl;

    @Column(name = "UNIONID")
    private String unionid;
}
