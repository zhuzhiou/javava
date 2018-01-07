package com.jeorgio.javava.thirdparty.zego.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@lombok.Data
public class CloseStreamVo implements Serializable {

    @JsonProperty("id")
    private int id;

    //关闭类型 0为正常关闭，非0为异常关闭（1为后台超时关闭，2为同一主播直播关闭之前没有关闭的流）
    @JsonProperty("type")
    private int type;

    @JsonProperty("channel_id")
    private String channelId;

    //流名 对应客户端StreamID 不超过255字节
    @JsonProperty("stream_alias")
    private String streamAlias;

    //第三方自定义数据 默认为空字符串 不超过255字节
    @JsonProperty("third_define_data")
    private String thirdDefineData;

    //服务器当前时间 Uinx时间戳
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("nonce")
    private String nonce;

    @JsonProperty("signature")
    private String signature;
}
