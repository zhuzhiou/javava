package cn.javava.api.live.service;

import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveRoomService {

    Page<LiveRoomVo> findLiveRooms(LiveRoomCriteria criteria, Pageable pageable);

}
