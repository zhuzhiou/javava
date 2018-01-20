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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lives")
@RestController
public class LiveController {

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
