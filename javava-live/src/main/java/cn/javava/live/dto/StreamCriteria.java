package cn.javava.live.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StreamCriteria implements Serializable {

    private String channelIdEq;

    private String streamAliasEq;

    private String publishNameEq;

    private LocalDateTime createDateGe;

    private String closeReasonEq;
}
