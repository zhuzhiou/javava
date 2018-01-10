package com.jeorgio.javava.live.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * 直播
 */
@Table(name = "jww_live")
@Entity
@DynamicUpdate
@Data
public class Live implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "STREAM_ALIAS")
    private String streamAlias;

    @Column(name = "PUBLISH_ID")
    private String publishId;

    @Column(name = "PUBLISH_NAME")
    private String publishName;

    @Column(name = "RTMP_URLS")
    private int rtmpUrls;

    @Column(name = "HLS_URLS")
    private int hlsUrls;

    @Column(name = "HDL_URLS")
    private int hdlUrls;

    @Column(name = "PIC_URLS")
    private int picUrls;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;

    @Column(name = "CLOSE_REASON")
    private String closeReason;
}
