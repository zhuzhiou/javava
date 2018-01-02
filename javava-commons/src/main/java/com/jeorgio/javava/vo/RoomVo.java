package com.jeorgio.javava.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RoomVo implements Serializable {

    private String channelId;

    //private String name;

    private String status;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
