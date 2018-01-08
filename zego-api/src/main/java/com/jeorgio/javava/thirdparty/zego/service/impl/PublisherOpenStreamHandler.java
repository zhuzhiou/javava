package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.jeorgio.javava.live.entity.Publisher;
import com.jeorgio.javava.live.repository.PublisherRepository;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.StringUtils.remove;

@Service("publisherOpenStreamHandler")
public class PublisherOpenStreamHandler implements OpenStreamHandler {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private TimeBasedGenerator timeBasedGenerator;

    @Override
    public void handle(OpenStreamVo vo) {
        Publisher publisher = publisherRepository.getPublisherByPublishId(vo.getPublishId());
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setId(remove(timeBasedGenerator.generate().toString(), "-"));
            publisher.setPublishId(vo.getPublishId());
            publisher.setCreateDate(ofEpochSecond(vo.getCreateTime(), 0, ZoneOffset.of("+8")));
        }
        publisher.setPublishName(vo.getPublishName());
        publisherRepository.save(publisher);
    }
}
