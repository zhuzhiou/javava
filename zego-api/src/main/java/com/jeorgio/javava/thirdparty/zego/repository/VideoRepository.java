package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.thirdparty.zego.entity.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video, String> {

    Video getVideoByRoomIdAndStreamAlias(String roomId, String streamAlias);

}
