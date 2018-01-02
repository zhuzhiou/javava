package com.jeorgio.javava.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * 直播流媒体
 */
@Table(name = "jww_live_stream")
@Entity
@DynamicUpdate
public class LiveStream implements Serializable {

    private String id;

    private Integer liveId;

    private String channelId;

    private String streamAlias;

    //使用closeReason来判断状态
    //private String streamState;

    private String publishId;

    private LocalDateTime createTime;

    private LocalDateTime closeTime;

    private String closeReason;

    @Column(name = "ID")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "LIVE_ID")
    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    @Column(name = "CHANNEL_ID")
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Column(name = "STREAM_ALIAS")
    public String getStreamAlias() {
        return streamAlias;
    }

    public void setStreamAlias(String streamAlias) {
        this.streamAlias = streamAlias;
    }

    @Column(name = "PUBLISH_ID")
    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    @Column(name = "CREATE_TIME")
    //@Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CLOSE_TIME")
    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    @Column(name = "CLOSE_REASON")
    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }
}
