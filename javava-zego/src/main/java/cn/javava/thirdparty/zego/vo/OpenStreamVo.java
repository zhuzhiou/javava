package cn.javava.thirdparty.zego.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@lombok.Data
public class OpenStreamVo implements Serializable {

    //Server端参数
    @JsonProperty("id")
    private Integer id;

    //频道ID，对应客户端RoomID，不超过255字节
    @JsonProperty("channel_id")
    private String channelId;

    //标题，不超过255字节
    @JsonProperty("title")
    private String title;

    //流名，对应客户端StreamID，不超过255字节
    @JsonProperty("stream_alias")
    private String streamAlias;

    //发布者ID，对应客户端UserID，不超过255字节
    @JsonProperty("publish_id")
    private String publishId;

    //发布者名字，对应客户端UserName，不超过255字节
    @JsonProperty("publish_name")
    private String publishName;

    //RTMP拉流地址，不超过1024字节
    @JsonProperty("rtmp_url")
    private String[] rtmpUrl;

    //HLS拉流地址，不超过1024字节
    @JsonProperty("hls_url")
    private String[] hlsUrl;

    //HDL拉流地址，不超过1024字节
    @JsonProperty("hdl_url")
    private String[] hdlUrl;

    //截图地址，不超过255字节
    @JsonProperty("pic_url")
    private String[] picUrl;

    //创建时间，Uinx时间戳
    @JsonProperty("create_time")
    private Long createTime;

    //服务器当前时间，Uinx时间戳
    @JsonProperty("timestamp")
    private String timestamp;

    //随机数
    @JsonProperty("nonce")
    private String nonce;

    //检验串，见检验说明
    @JsonProperty("signature")
    private String signature;
}
