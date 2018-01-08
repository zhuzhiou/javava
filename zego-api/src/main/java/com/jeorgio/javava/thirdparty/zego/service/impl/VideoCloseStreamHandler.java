package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.live.entity.Video;
import com.jeorgio.javava.live.repository.VideoRepository;
import com.jeorgio.javava.thirdparty.zego.service.CloseStreamHandler;
import com.jeorgio.javava.thirdparty.zego.util.ZegoConstants;
import com.jeorgio.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("videoCloseStreamHandler")
public class VideoCloseStreamHandler implements CloseStreamHandler {

    @Autowired
    private VideoRepository videoRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        Video video = videoRepository.getVideoByRoomIdAAndStreamAlias(vo.getChannelId(), vo.getStreamAlias());
        if (video != null) {
            switch (vo.getType()) {
                case 0:
                    video.setCloseReason(ZegoConstants.CLOSE_REASON_LEGAL);
                    break;
                case 1:
                    video.setCloseReason(ZegoConstants.CLOSE_REASON_TIMEOUT);
                    break;
                case 2:
                    video.setCloseReason(ZegoConstants.CLOSE_REASON_PREVIOUS);
                    break;
            }
            video.setCloseDate(LocalDateTime.now());
            videoRepository.save(video);
        }
    }
}
