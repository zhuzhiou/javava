package cn.javava.shelf.repository;

import cn.javava.shelf.entity.ShelfRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Repository
public interface ShelfRecordRepository extends JpaRepository<ShelfRecord,Long> {

}
