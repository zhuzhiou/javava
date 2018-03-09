package cn.javava.live.controller;

import cn.javava.common.wrap.PageWrapper;
import cn.javava.live.dto.RoomCriteria;
import cn.javava.live.entity.Room;
import cn.javava.live.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rooms")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public PageWrapper<Room> getRooms(RoomCriteria criteria, @PageableDefault Pageable pageable) {
        Page<Room> page = roomService.findRooms(criteria, pageable);
        PageWrapper<Room> wrapper = new PageWrapper<>(0, "成功", page);
        return wrapper;
    }
}
