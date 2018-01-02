package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.entity.LiveRoom;
import org.springframework.data.repository.CrudRepository;

public interface LiveRoomRepository extends CrudRepository<LiveRoom, String> {

    LiveRoom getLiveRoomByChannelId(String channelId);

}
