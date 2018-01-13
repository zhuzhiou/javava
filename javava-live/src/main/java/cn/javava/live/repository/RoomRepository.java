package cn.javava.live.repository;

import cn.javava.live.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

    Room getRoomByRoomId(String roomId);

}
