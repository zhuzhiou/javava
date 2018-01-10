package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.live.service.LiveService;
import com.jeorgio.javava.live.vo.RoomVo;
import com.jeorgio.javava.thirdparty.zego.service.OpenLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenLiveVo;
import org.springframework.stereotype.Service;

@Service("openLiveHandler_room")
public class OpenLiveHandler_room implements OpenLiveHandler {

    @Reference
    private LiveService liveService;

    @Override
    public void handle(OpenLiveVo vo) {
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomId(vo.getChannelId());
        roomVo.setRoomName(vo.getTitle());
        roomVo.setRoomState("ON");
        liveService.saveRoom(roomVo);
    }
}
