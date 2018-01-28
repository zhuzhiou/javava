package cn.javava.live.repository;

import cn.javava.live.entity.LiveRoomPo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface LiveRoomRepository extends Repository<LiveRoomPo, String>, JpaSpecificationExecutor<LiveRoomPo> {

}
