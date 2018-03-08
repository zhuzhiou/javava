package cn.javava.live.controller;

import cn.javava.common.PageWrapper;
import cn.javava.live.dto.RoomCriteria;
import cn.javava.live.dto.StreamCriteria;
import cn.javava.live.entity.Room;
import cn.javava.live.entity.Stream;
import cn.javava.live.service.RoomService;
import cn.javava.live.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private StreamService streamService;

    @GetMapping(path = "/rooms")
    public PageWrapper<Room> getRooms(RoomCriteria criteria, @PageableDefault Pageable pageable) {
        Page<Room> page = roomService.findRooms(criteria, pageable);
        PageWrapper<Room> wrapper = new PageWrapper<>(0, "成功", page);
        return wrapper;
    }

    @GetMapping(path = "/streams")
    public PageWrapper<Stream> getStreams(StreamCriteria criteria, @PageableDefault Pageable pageable) {
        Page<Stream> page = streamService.findStreams(criteria, pageable);
        PageWrapper<Stream> wrapper = new PageWrapper<>(0, "成功", page);
        return wrapper;
    }

}
