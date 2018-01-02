package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.entity.LivePublisher;
import org.springframework.data.repository.CrudRepository;

public interface LivePublisherRepository extends CrudRepository<LivePublisher, String> {

    LivePublisher getLivePublisherByPublishId(String publishId);

}
