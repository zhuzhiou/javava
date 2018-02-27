package cn.javava.live.repository;

import cn.javava.live.entity.Stream;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface StreamRepository extends Repository<Stream, String>, JpaSpecificationExecutor<Stream> {

}
