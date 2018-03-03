package cn.javava.live.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 房间
 */
@StaticMetamodel(Room.class)
public final class Room_ {

    public static volatile SingularAttribute<Room, String> name;

    public static volatile SingularAttribute<Room, String> state;

    private Room_() {
    }
}
