package cn.javava.user.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserPo.class)
public final class UserPo_ {

    public static volatile SingularAttribute<UserPo, String> nickname;

    public static volatile SingularAttribute<UserPo, String> sex;

    public static volatile SingularAttribute<UserPo, String> country;

    public static volatile SingularAttribute<UserPo, String> province;

    public static volatile SingularAttribute<UserPo, String> city;

    private UserPo_() {
    }
}