package cn.javava.live.service;

import cn.javava.live.vo.LiveRoomCriteria;
import cn.javava.live.vo.LiveRoomVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveRoomService {

    Page<LiveRoomVo> findLiveRooms(LiveRoomCriteria criteria, Pageable pageable);

}
