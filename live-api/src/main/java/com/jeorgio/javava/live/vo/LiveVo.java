package com.jeorgio.javava.live.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LiveVo implements Serializable {

    private String roomId;

    private String streamAlias;

    private String publishId;

    private String publishName;

    private int rtmpUrls;

    private int hlsUrls;

    private int hdlUrls;

    private int picUrls;

    private LocalDateTime createDate;

    private String closeReason;
}
