package cn.javava.live.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveRoomVo implements Serializable {

    private String roomId;

    private String roomName;

    private String roomState;
}
