package com.jeorgio.javava.thirdparty.zego.repository;

import com.jeorgio.javava.thirdparty.zego.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

    Room getRoomByRoomId(String roomId);

}
