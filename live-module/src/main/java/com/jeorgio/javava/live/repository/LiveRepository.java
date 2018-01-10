package com.jeorgio.javava.live.repository;

import com.jeorgio.javava.live.entity.Live;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveRepository extends CrudRepository<Live, String> {

    Live getLiveByRoomIdAndStreamAlias(String roomId, String streamAlias);

}
