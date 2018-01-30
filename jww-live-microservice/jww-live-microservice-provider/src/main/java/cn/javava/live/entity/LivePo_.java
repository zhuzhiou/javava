package cn.javava.live.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

/***
 * 直播
 */
@StaticMetamodel(LivePo.class)
public class LivePo_ {

    public static volatile SingularAttribute<LivePo, String> liveId;

    public static volatile SingularAttribute<LivePo, String> channelId;

    public static volatile SingularAttribute<LivePo, String> streamAlias;

    public static volatile SingularAttribute<LivePo, String> publishName;

    public static volatile SingularAttribute<LivePo, LocalDateTime> createDate;

    public static volatile SingularAttribute<LivePo, String> closeReason;

    private LivePo_() {
    }
}
