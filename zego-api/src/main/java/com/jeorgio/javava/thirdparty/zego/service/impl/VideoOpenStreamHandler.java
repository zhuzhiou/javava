package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.thirdparty.zego.entity.Video;
import com.jeorgio.javava.thirdparty.zego.repository.VideoRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("videoOpenStreamHandler")
public class VideoOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        Video video = new Video();
        video.setId(remove(timeBasedGenerator.generate().toString(), "-"));
        video.setRoomId(vo.getChannelId());
        video.setStreamAlias(vo.getStreamAlias());
        video.setPublishId(vo.getPublishId());
        video.setRtmpUrls(join(vo.getRtmpUrl(), ","));
        video.setCreateDate(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        videoRepository.save(video);
    }
}
