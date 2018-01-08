package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.thirdparty.zego.entity.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, String> {

    Publisher getPublisherByPublishId(String publishId);

}
