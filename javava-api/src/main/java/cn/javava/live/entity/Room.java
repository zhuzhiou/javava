package cn.javava.live.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 房间
 */
@Table(name = "jww_live_room")
@Entity
@DynamicUpdate
@Data
public class Room implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATE")
    private String state;

    @Column(name = "DOLL_TYPE")
    private Long dollType;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}
