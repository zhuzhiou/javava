package cn.javava.api.live.service;

import cn.javava.api.live.vo.LiveCriteria;
import cn.javava.api.live.vo.LiveRoomCriteria;
import cn.javava.api.live.vo.LiveRoomVo;
import cn.javava.api.live.vo.LiveVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveService {

    Page<LiveVo> findLives(LiveCriteria criteria, Pageable pageable);

    Page<LiveRoomVo> findRooms(LiveRoomCriteria criteria, Pageable pageable);

}
