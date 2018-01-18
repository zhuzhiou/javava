package cn.javava.api.live.controller;

import cn.javava.api.commons.controller.BaseApiController;
import cn.javava.api.live.service.LiveRoomService;
import cn.javava.api.live.service.LiveService;
import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import cn.javava.api.live.vo.LiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lives")
@RestController
public class LiveController extends BaseApiController {

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
    private LiveService liveService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<LiveVo> getLives(LiveCriteria criteria, Pageable pageable) {
        return liveService.findLives(criteria, pageable);
    }

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<LiveRoomVo> getLiveRooms(LiveRoomCriteria criteria, Pageable pageable) {
        return liveRoomService.findLiveRooms(criteria, pageable);
    }
}
