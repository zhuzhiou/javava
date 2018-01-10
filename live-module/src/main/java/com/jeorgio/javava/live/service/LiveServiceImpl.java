package com.jeorgio.javava.live.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.live.entity.Live;
import com.jeorgio.javava.live.entity.Room;
import com.jeorgio.javava.live.repository.LiveRepository;
import com.jeorgio.javava.live.repository.RoomRepository;
import com.jeorgio.javava.live.vo.LiveVo;
import com.jeorgio.javava.live.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.remove;

@Service(timeout = 60000, delay = -1)
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveRepository liveRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveRoom(RoomVo vo) {
        Room room = roomRepository.getRoomByRoomId(vo.getRoomId());
        if (room == null) {
            room = new Room();
            room.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            room.setRoomId(vo.getRoomId());
            room.setCreateDate(LocalDateTime.now());
        }
        room.setRoomState(vo.getRoomState());
        roomRepository.save(room);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveLive(LiveVo vo) {
        if (isBlank(vo.getCloseReason())) {
            Live live = new Live();
            live.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            live.setRoomId(vo.getRoomId());
            live.setStreamAlias(vo.getStreamAlias());
            live.setPublishId(vo.getPublishId());
            live.setRtmpUrls(vo.getRtmpUrls());
            live.setHdlUrls(vo.getHdlUrls());
            live.setHlsUrls(vo.getHlsUrls());
            live.setCreateDate(vo.getCreateDate());
            liveRepository.save(live);
        } else {
            Live live = liveRepository.getLiveByRoomIdAndStreamAlias(vo.getRoomId(), vo.getStreamAlias());
            live.setCloseReason(vo.getCloseReason());
            live.setCloseDate(LocalDateTime.now());
        }
    }
}
