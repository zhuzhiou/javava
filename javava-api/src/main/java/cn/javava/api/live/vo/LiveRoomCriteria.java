package cn.javava.api.live.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveRoomCriteria implements Serializable {

    private String roomName;

    private String roomState;
}
