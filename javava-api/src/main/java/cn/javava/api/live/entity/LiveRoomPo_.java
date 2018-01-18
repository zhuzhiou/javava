package cn.javava.api.live.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 房间
 */
@StaticMetamodel(LiveRoomPo.class)
public final class LiveRoomPo_ {

    public static volatile SingularAttribute<LiveRoomPo, String> name;

    public static volatile SingularAttribute<LiveRoomPo, String> state;

    private LiveRoomPo_() {
    }
}
