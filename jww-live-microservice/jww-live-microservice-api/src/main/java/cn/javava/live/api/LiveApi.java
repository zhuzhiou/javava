package cn.javava.live.api;

import cn.javava.live.vo.LiveCriteria;
import cn.javava.live.vo.LiveRoomCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface LiveApi {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult getLives(LiveCriteria criteria, Pageable pageable);

    @GetMapping(path = "/rooms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult getLiveRooms(LiveRoomCriteria criteria, Pageable pageable);
}
