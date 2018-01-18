package cn.javava.api.live.facade;

import cn.javava.api.live.service.LiveRoomService;
import cn.javava.api.live.service.LiveService;
import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import cn.javava.api.live.vo.LiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LiveFacade {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private LiveService liveService;

    public Page<LiveRoomVo> findLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        return liveRoomService.findLiveRooms(criteria, pageable);
    }

    public Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable) {
        return liveService.findLives(criteria, pageable);
    }
}
