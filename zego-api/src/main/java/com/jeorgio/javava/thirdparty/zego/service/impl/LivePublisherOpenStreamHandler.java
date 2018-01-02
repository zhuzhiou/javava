package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.entity.LivePublisher;
import com.jeorgio.javava.thirdparty.zego.OpenStreamVo;
import com.jeorgio.javava.thirdparty.zego.repository.LivePublisherRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("livePublisherOpenStreamHandler")
public class LivePublisherOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private LivePublisherRepository livePublisherRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        LivePublisher publisher = livePublisherRepository.getLivePublisherByPublishId(vo.getPublishId());
        if (publisher == null) {
            publisher = new LivePublisher();
            publisher.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            publisher.setPublishId(vo.getPublishId());
            publisher.setCreateTime(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        }
        publisher.setPublishName(vo.getPublishName());
        livePublisherRepository.save(publisher);
    }
}
