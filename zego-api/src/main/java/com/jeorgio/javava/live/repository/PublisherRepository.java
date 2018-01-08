package com.jeorgio.javava.live.repository;

import com.jeorgio.javava.live.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, String> {

    Publisher getPublisherByPublishId(String publishId);

}
