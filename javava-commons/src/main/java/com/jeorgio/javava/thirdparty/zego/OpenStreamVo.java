package com.jeorgio.javava.thirdparty.zego;

import java.io.Serializable;

public class OpenStreamVo implements Serializable {

    //Server端参数
    private Integer id;

    //Server端参数
    private Integer live_id;

    //频道ID，对应客户端RoomID，不超过255字节
    private String channel_id;

    //标题，不超过255字节
    private String title;

    //流名，对应客户端StreamID，不超过255字节
    private String stream_alias;

    //发布者ID，对应客户端UserID，不超过255字节
    private String publish_id;

    //发布者名字，对应客户端UserName，不超过255字节
    private String publish_name;

    //RTMP拉流地址，不超过1024字节
    private String[] rtmp_url;

    //HLS拉流地址，不超过1024字节
    private String[] hls_url;

    //HDL拉流地址，不超过1024字节
    private String[] hdl_url;

    //截图地址，不超过255字节
    private String[] pic_url;

    //创建时间，Uinx时间戳
    private Long create_time;

    //服务器当前时间，Uinx时间戳
    private String timestamp;

    //随机数
    private String nonce;

    //检验串，见检验说明
    private String signature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiveId() {
        return live_id;
    }

    public void setLiveId(Integer liveId) {
        this.live_id = liveId;
    }

    public String getChannelId() {
        return channel_id;
    }

    public void setChannelId(String channelId) {
        this.channel_id = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreamAlias() {
        return stream_alias;
    }

    public void setStreamAlias(String stream_alias) {
        this.stream_alias = stream_alias;
    }

    public String getPublishId() {
        return publish_id;
    }

    public void setPublishId(String publishId) {
        this.publish_id = publishId;
    }

    public String getPublishName() {
        return publish_name;
    }

    public void setPublishName(String publishName) {
        this.publish_name = publishName;
    }

    public String[] getRtmpUrl() {
        return rtmp_url;
    }

    public void setRtmpUrl(String[] rtmpUrl) {
        this.rtmp_url = rtmpUrl;
    }

    public String[] getHlsUrl() {
        return hls_url;
    }

    public void setHlsUrl(String[] hlsUrl) {
        this.hls_url = hlsUrl;
    }

    public String[] getHdlUrl() {
        return hdl_url;
    }

    public void setHdlUrl(String[] hdlUrl) {
        this.hdl_url = hdlUrl;
    }

    public String[] getPicUrl() {
        return pic_url;
    }

    public void setPicUrl(String[] picUrl) {
        this.pic_url = picUrl;
    }

    public Long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Long createTime) {
        this.create_time = createTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
