package com.jeorgio.javava.live.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * 直播
 */
@Table(name = "jww_live_video")
@Entity
@DynamicUpdate
@Data
public class Video implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "STREAM_ALIAS")
    private String streamAlias;

    @Column(name = "PUBLISH_ID")
    private String publishId;

    @Column(name = "RTMP_URLS")
    private String rtmpUrls;

    @Column(name = "HLS_URLS")
    private String hlsUrls;

    @Column(name = "HDL_URLS")
    private String hdlUrls;

    @Column(name = "PIC_URLS")
    private String picUrls;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;

    @Column(name = "CLOSE_REASON")
    private String closeReason;
}
