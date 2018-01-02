package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.entity.LiveStream;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LiveStreamRepository extends CrudRepository<LiveStream, String> {

    @Query(value = "select a.* from jww_live_stream a inner join jww_live_room b on a.LIVE_ID = b.LIVE_ID where b.channel_id = ?1", nativeQuery = true)
    LiveStream getLiveStreamByChannelId(String channelId);

}
