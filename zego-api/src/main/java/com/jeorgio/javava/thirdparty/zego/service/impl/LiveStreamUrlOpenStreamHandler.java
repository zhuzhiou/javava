package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.config.LiveConstants;
import com.jeorgio.javava.entity.LiveStreamUrl;
import com.jeorgio.javava.thirdparty.zego.repository.LiveStreamUrlRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("liveStreamUrlOpenStreamHandler")
public class LiveStreamUrlOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private LiveStreamUrlRepository liveStreamUrlRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        List<LiveStreamUrl> streamUrls = new ArrayList<>();
        if (isNotEmpty(vo.getRtmpUrl())) {
            String[] rtmpUrls = vo.getRtmpUrl();
            for (int i = 0; i < rtmpUrls.length; i++) {
                String rtmpUrl = rtmpUrls[i];

                LiveStreamUrl streamUrl = new LiveStreamUrl();
                streamUrl.setId(remove(timeBasedGenerator.generate().toString(), "-"));
                streamUrl.setLiveId(vo.getLiveId());
                streamUrl.setStreamProtocol(LiveConstants.LIVE_PROTOCOL_RTMP);
                streamUrl.setStreamOrder(i);
                streamUrl.setStreamUrl(rtmpUrl);

                streamUrls.add(streamUrl);
            }
        }
        liveStreamUrlRepository.saveAll(streamUrls);
    }
}
