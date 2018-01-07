package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.config.LiveConstants;
import com.jeorgio.javava.entity.LiveRoom;
import com.jeorgio.javava.thirdparty.zego.repository.LiveRoomRepository;
import com.jeorgio.javava.thirdparty.zego.service.CloseStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("liveRoomCloseStreamHandler")
public class LiveRoomCloseStreamHandler implements CloseStreamHandler {

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        LiveRoom room = liveRoomRepository.getLiveRoomByChannelId(vo.getChannelId());
        if (room != null) {
            room.setChannelState(LiveConstants.ROOM_STATE_STOP_LIVE);
            liveRoomRepository.save(room);
        }
    }
}
