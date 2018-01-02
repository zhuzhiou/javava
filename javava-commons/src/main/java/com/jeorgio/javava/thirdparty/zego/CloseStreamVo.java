package com.jeorgio.javava.thirdparty.zego;

import java.io.Serializable;

public class CloseStreamVo implements Serializable {

    private int id;

    //关闭类型 0为正常关闭，非0为异常关闭（1为后台超时关闭，2为同一主播直播关闭之前没有关闭的流）
    private int type;

    private String channel_id;

    //流名 对应客户端StreamID 不超过255字节
    private String stream_alias;

    //第三方自定义数据 默认为空字符串 不超过255字节
    private String third_define_data;

    //服务器当前时间 Uinx时间戳
    private String timestamp;

    private String nonce;

    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChannelId() {
        return channel_id;
    }

    public void setChannelId(String channelId) {
        this.channel_id = channelId;
    }

    public String getStreamAlias() {
        return stream_alias;
    }

    public void setStreamAlias(String streamAlias) {
        this.stream_alias = streamAlias;
    }

    public String getThirdDefineData() {
        return third_define_data;
    }

    public void setThird_define_data(String thirdDefineData) {
        this.third_define_data = thirdDefineData;
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
