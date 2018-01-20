package cn.javava.live.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LiveCriteria implements Serializable {

    private String streamAliasEq;

    private String publishNameEq;

    private LocalDateTime createDateGe;

    private String closeReasonEq;
}
