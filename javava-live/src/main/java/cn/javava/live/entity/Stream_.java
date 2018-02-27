package cn.javava.live.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

/***
 * 直播
 */
@StaticMetamodel(Stream.class)
public class Stream_ {

    public static volatile SingularAttribute<Stream, String> channelId;

    public static volatile SingularAttribute<Stream, String> streamAlias;

    public static volatile SingularAttribute<Stream, String> publishName;

    public static volatile SingularAttribute<Stream, LocalDateTime> createDate;

    public static volatile SingularAttribute<Stream, String> closeReason;

    private Stream_() {
    }
}
