package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.config.LiveConstants;
import com.jeorgio.javava.entity.LiveStream;
import com.jeorgio.javava.thirdparty.zego.repository.LiveStreamRepository;
import com.jeorgio.javava.thirdparty.zego.service.CloseStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("liveStreamCloseStreamHandler")
public class LiveStreamCloseStreamHandler implements CloseStreamHandler {

    @Autowired
    private LiveStreamRepository liveStreamRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        LiveStream stream = liveStreamRepository.getLiveStreamByChannelId(vo.getChannelId());
        if (stream != null) {
            switch (vo.getType()) {
                case 0:
                    stream.setCloseReason(LiveConstants.CLOSE_REASON_LEGAL);
                    break;
                case 1:
                    stream.setCloseReason(LiveConstants.CLOSE_REASON_TIMEOUT);
                    break;
                case 2:
                    stream.setCloseReason(LiveConstants.CLOSE_REASON_PREVIOUS);
                    break;
            }
            stream.setCloseTime(LocalDateTime.now());
            liveStreamRepository.save(stream);
        }
    }
}
