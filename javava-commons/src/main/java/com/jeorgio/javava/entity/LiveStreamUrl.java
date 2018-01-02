package com.jeorgio.javava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 直播流媒体的地址
 */
@Table(name = "jww_live_stream_url")
@Entity
public class LiveStreamUrl {

    private String id;

    private Integer liveId;

    private String streamProtocol;

    private String streamUrl;

    private Integer streamOrder;

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

    @Column(name = "STREAM_PROTOCOL")
    public String getStreamProtocol() {
        return streamProtocol;
    }

    public void setStreamProtocol(String streamProtocol) {
        this.streamProtocol = streamProtocol;
    }

    @Column(name = "STREAM_URL")
    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @Column(name = "STREAM_ORDER")
    public Integer getStreamOrder() {
        return streamOrder;
    }

    public void setStreamOrder(Integer streamOrder) {
        this.streamOrder = streamOrder;
    }
}
