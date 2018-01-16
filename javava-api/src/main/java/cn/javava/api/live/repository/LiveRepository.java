package cn.javava.api.live.repository;

import cn.javava.api.live.entity.LivePo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface LiveRepository extends Repository<LivePo, String> {

    Page<LivePo> findAll(Pageable pageable);

}
