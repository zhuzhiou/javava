package cn.javava.live.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomCriteria implements Serializable {

    private String name;

    private String state;
}
