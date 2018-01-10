package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeorgio.javava.live.service.LiveService;
import com.jeorgio.javava.live.vo.LiveVo;
import com.jeorgio.javava.thirdparty.zego.service.OpenLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenLiveVo;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.ArrayUtils.getLength;

@Service("openLiveHandler_live")
public class OpenLiveHandler_live implements OpenLiveHandler {

    @Reference
    private LiveService liveService;

    @Override
    public void handle(OpenLiveVo vo) {
        LiveVo liveVo = new LiveVo();
        liveVo.setRoomId(vo.getChannelId());
        liveVo.setStreamAlias(vo.getStreamAlias());
        liveVo.setPublishId(vo.getPublishId());
        liveVo.setPublishName(vo.getPublishName());
        liveVo.setRtmpUrls(getLength(vo.getRtmpUrl()));
        liveVo.setHdlUrls(getLength(vo.getHdlUrl()));
        liveVo.setHlsUrls(getLength(vo.getHlsUrl()));
        liveVo.setPicUrls(getLength(vo.getPicUrl()));
        liveVo.setCreateDate(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        liveService.saveLive(liveVo);
    }
}
