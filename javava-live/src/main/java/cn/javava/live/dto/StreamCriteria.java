package cn.javava.live.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StreamCriteria implements Serializable {

    private String channelId;

    private String streamAlias;

    private String publishName;
}
