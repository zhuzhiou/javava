package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.entity.LiveStream;
import com.jeorgio.javava.thirdparty.zego.repository.LiveStreamRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("liveStreamOpenStreamHandler")
public class LiveStreamOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private LiveStreamRepository liveStreamRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        LiveStream stream = new LiveStream();
        stream.setId(remove(timeBasedGenerator.generate().toString(), "-"));
        stream.setLiveId(vo.getLiveId());
        stream.setChannelId(vo.getChannelId());
        stream.setStreamAlias(vo.getStreamAlias());
        stream.setPublishId(vo.getPublishId());
        stream.setCreateTime(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        liveStreamRepository.save(stream);
    }
}
