package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.thirdparty.zego.entity.Room;
import com.jeorgio.javava.thirdparty.zego.repository.RoomRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("roomOpenStreamHandler")
public class RoomOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        Room room = roomRepository.getRoomByRoomId(vo.getChannelId());
        if (room == null) {
            room = new Room();
            room.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            room.setRoomId(vo.getChannelId());
            room.setCreateDate(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        }
        room.setRoomName(vo.getTitle());
        room.setRoomState("ON");
        roomRepository.save(room);
    }
}
