package cn.javava.live.repository;

import cn.javava.live.entity.Room;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface RoomRepository extends Repository<Room, String>, JpaSpecificationExecutor<Room> {

}
