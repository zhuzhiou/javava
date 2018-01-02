package com.jeorgio.javava.api.web;

import com.jeorgio.javava.entity.LiveRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/liveRoom")
@RestController
public class LiveRoomController {

    @GetMapping("/")
    public LiveRoom doGet() {
        return null;
    }
}
