package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.live.service.LiveService;
import com.jeorgio.javava.live.vo.LiveVo;
import com.jeorgio.javava.thirdparty.zego.service.CloseLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseLiveVo;
import org.springframework.stereotype.Service;

@Service("closeLiveHandler_live")
public class CloseLiveHandler_live implements CloseLiveHandler {

    @Reference
    private LiveService liveService;

    @Override
    public void handle(CloseLiveVo vo) {
        LiveVo liveVo = new LiveVo();
        liveVo.setRoomId(vo.getChannelId());
        liveVo.setStreamAlias(vo.getStreamAlias());
        liveVo.setCloseReason(String.valueOf(vo.getType()));
        liveService.saveLive(liveVo);
    }
}
