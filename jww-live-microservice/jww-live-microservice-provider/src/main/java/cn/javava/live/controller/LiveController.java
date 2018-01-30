package cn.javava.live.controller;

import cn.javava.live.api.ApiResult;
import cn.javava.live.api.LiveApi;
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
public class LiveController implements LiveApi {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private LiveService liveService;

    @Override
    public ApiResult getLives(LiveCriteria criteria, Pageable pageable) {
        Page<LiveVo> page = liveService.findLives(criteria, pageable);
        return ApiResult.success(page);
    }

    @Override
    public ApiResult getLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        Page<LiveRoomVo> page = liveRoomService.findLiveRooms(criteria, pageable);
        return ApiResult.success(page);
    }
}
