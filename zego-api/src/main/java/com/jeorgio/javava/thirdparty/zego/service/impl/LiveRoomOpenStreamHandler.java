package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.config.LiveConstants;
import com.jeorgio.javava.entity.LiveRoom;
import com.jeorgio.javava.thirdparty.zego.OpenStreamVo;
import com.jeorgio.javava.thirdparty.zego.repository.LiveRoomRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("liveRoomOpenStreamHandler")
public class LiveRoomOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        LiveRoom room = liveRoomRepository.getLiveRoomByChannelId(vo.getChannelId());
        if (room == null) {
            room = new LiveRoom();
            room.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            room.setChannelId(vo.getChannelId());
            room.setCreateTime(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        }
        room.setChannelName(vo.getTitle());
        room.setLiveId(vo.getLiveId());
        room.setChannelState(LiveConstants.ROOM_STATE_ON_LIVE);
        liveRoomRepository.save(room);
    }
}
