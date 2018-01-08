package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.thirdparty.zego.entity.Room;
import com.jeorgio.javava.thirdparty.zego.repository.RoomRepository;
import com.jeorgio.javava.thirdparty.zego.service.CloseStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roomCloseStreamHandler")
public class RoomCloseStreamHandler implements CloseStreamHandler {

    @Autowired
    private RoomRepository roomRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        Room room = roomRepository.getRoomByRoomId(vo.getChannelId());
        if (room != null) {
            room.setRoomState("OFF");
            roomRepository.save(room);
        }
    }
}
