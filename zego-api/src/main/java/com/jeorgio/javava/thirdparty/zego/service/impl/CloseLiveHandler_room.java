package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.live.service.LiveService;
import com.jeorgio.javava.live.vo.RoomVo;
import com.jeorgio.javava.thirdparty.zego.service.CloseLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseLiveVo;
import org.springframework.stereotype.Service;

@Service("closeLiveHandler_room")
public class CloseLiveHandler_room implements CloseLiveHandler {

    @Reference
    private LiveService liveService;

    @Override
    public void handle(CloseLiveVo vo) {
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomId(vo.getChannelId());
        roomVo.setRoomState("OFF");
        liveService.saveRoom(roomVo);
    }
}
