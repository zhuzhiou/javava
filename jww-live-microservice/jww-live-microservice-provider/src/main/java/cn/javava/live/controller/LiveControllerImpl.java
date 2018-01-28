package cn.javava.live.controller;

import cn.javava.live.service.LiveRoomService;
import cn.javava.live.service.LiveService;
import cn.javava.live.vo.LiveCriteria;
import cn.javava.live.vo.LiveRoomCriteria;
import cn.javava.live.vo.LiveRoomVo;
import cn.javava.live.vo.LiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveControllerImpl implements LiveController {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private LiveService liveService;

    @Override
    public Page<LiveVo> getLives(LiveCriteria criteria, Pageable pageable) {
        return liveService.findLives(criteria, pageable);
    }

    @Override
    public Page<LiveRoomVo> getLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        return liveRoomService.findLiveRooms(criteria, pageable);
    }
}
