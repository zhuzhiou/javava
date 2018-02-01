package cn.javava.live.controller;

import cn.javava.live.api.LiveApi;
import cn.javava.live.service.LiveRoomService;
import cn.javava.live.service.LiveService;
import cn.javava.live.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveController implements LiveApi {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private LiveService liveService;

    @Override
    public ResultVo<Page<LiveVo>> getLives(LiveCriteria criteria, Pageable pageable) {
        Page<LiveVo> page = liveService.findLives(criteria, pageable);
        return ResultVo.success(page);
    }

    @Override
    public ResultVo<Page<LiveRoomVo>> getLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        Page<LiveRoomVo> page = liveRoomService.findLiveRooms(criteria, pageable);
        return ResultVo.success(page);
    }
}
