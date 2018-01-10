package com.jeorgio.javava.live.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RoomVo implements Serializable {

    private String roomId;

    private String roomName;

    private String roomState;
}
