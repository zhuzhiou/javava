package cn.javava.live.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomCriteria implements Serializable {

    private String nameEq;

    private String nameLike;

    private String stateEq;
}
