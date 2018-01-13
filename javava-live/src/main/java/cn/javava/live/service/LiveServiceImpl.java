package cn.javava.live.service;

import cn.javava.live.repository.LiveRepository;
import cn.javava.live.repository.RoomRepository;
import cn.javava.live.vo.LiveRoomVo;
import cn.javava.live.vo.LiveVo;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveRepository liveRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveRoom(LiveRoomVo vo) {

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveLive(LiveVo vo) {

    }
}
