package com.jeorgio.javava.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 根据closeReason判断当前流是否可用
 */
public class LiveVo implements Serializable {

    private Integer liveId;

    private String title;

    private String[] rtmpUrls;

    private LocalDateTime createTime;

    private LocalDateTime closeTime;

    private String closeReason;

    //@JsonProperty("live_id")
    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    //@JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //@JsonProperty("rtmp_urls")
    public String[] getRtmpUrls() {
        return rtmpUrls;
    }

    public void setRtmpUrls(String[] rtmpUrls) {
        this.rtmpUrls = rtmpUrls;
    }

    //@JsonProperty("create_time")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    //@JsonProperty("close_time")
    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    //@JsonProperty("close_reason")
    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }
}
