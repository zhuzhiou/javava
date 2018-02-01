package cn.javava.live.api;

import cn.javava.live.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lives")
public interface LiveApi {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultVo<Page<LiveVo>> getLives(LiveCriteria criteria, Pageable pageable);

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultVo<Page<LiveRoomVo>> getLiveRooms(LiveRoomCriteria criteria, Pageable pageable);
}
