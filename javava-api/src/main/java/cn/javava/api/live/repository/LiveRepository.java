package cn.javava.api.live.repository;

import cn.javava.api.live.entity.LivePo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface LiveRepository extends Repository<LivePo, String>, JpaSpecificationExecutor<LivePo> {

}
