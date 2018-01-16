package cn.javava.api.live.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LiveCriteria implements Serializable {

    private String streamAlias;

    private String publishName;

    private LocalDateTime createDate;

    private String closeReason;
}
