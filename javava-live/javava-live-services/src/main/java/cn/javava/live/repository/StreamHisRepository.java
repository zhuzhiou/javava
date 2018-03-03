package cn.javava.live.repository;

import cn.javava.live.entity.StreamHis;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface StreamHisRepository extends Repository<StreamHis, String>, JpaSpecificationExecutor<StreamHis> {

}
