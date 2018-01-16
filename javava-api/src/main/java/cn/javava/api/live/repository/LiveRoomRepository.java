package cn.javava.api.live.repository;

import cn.javava.api.live.entity.LiveRoomPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface LiveRoomRepository extends Repository<LiveRoomPo, String> {

    Page<LiveRoomPo> findAll(Pageable var1);
}
