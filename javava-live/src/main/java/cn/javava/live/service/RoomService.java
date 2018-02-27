package cn.javava.live.service;

import cn.javava.live.dto.RoomCriteria;
import cn.javava.live.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    Page<Room> findRooms(RoomCriteria criteria, Pageable pageable);

}
