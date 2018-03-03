package cn.javava.live.entity;

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
@Table(name = "jww_live_stream")
@Entity
@DynamicUpdate
@Data
public class Stream implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @Column(name = "STREAM_ALIAS")
    private String streamAlias;

    @Column(name = "PUBLISH_ID")
    private String publishId;

    @Column(name = "PUBLISH_NAME")
    private String publishName;

    @Column(name = "RTMP_URL")
    private String rtmpUrl;

    @Column(name = "HLS_URL")
    private String hlsUrl;

    @Column(name = "HDL_URL")
    private String hdlUrl;

    @Column(name = "PIC_URL")
    private String picUrl;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}
