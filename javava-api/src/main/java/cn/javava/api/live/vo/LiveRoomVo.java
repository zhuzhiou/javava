package cn.javava.api.live.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveRoomVo implements Serializable {

    private String channelId;

    private String name;

    private String state;
}
