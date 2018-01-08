package com.jeorgio.javava.live.repository;

import com.jeorgio.javava.live.entity.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, String> {

    Video getVideoByRoomIdAAndStreamAlias(String roomId, String streamAlias);

}
