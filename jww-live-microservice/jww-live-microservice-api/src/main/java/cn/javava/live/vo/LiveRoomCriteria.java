package cn.javava.live.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveRoomCriteria implements Serializable {

    private String nameEq;

    private String nameLike;

    private String stateEq;
}
