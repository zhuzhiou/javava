package cn.javava.user.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public final class User_ {

    public static volatile SingularAttribute<User, String> nickname;

    public static volatile SingularAttribute<User, String> sex;

    public static volatile SingularAttribute<User, String> country;

    public static volatile SingularAttribute<User, String> province;

    public static volatile SingularAttribute<User, String> city;

    private User_() {
    }
}