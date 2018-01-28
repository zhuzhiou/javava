package cn.javava.live.controller;

import cn.javava.live.vo.LiveCriteria;
import cn.javava.live.vo.LiveRoomCriteria;
import cn.javava.live.vo.LiveRoomVo;
import cn.javava.live.vo.LiveVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface LiveController {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Page<LiveVo> getLives(LiveCriteria criteria, Pageable pageable);

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Page<LiveRoomVo> getLiveRooms(LiveRoomCriteria criteria, Pageable pageable);
}
