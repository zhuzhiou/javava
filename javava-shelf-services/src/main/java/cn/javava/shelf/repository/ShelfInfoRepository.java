package cn.javava.shelf.repository;

import cn.javava.shelf.entity.ShelfInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Repository
public interface ShelfInfoRepository extends JpaRepository<ShelfInfo,Long> {
}
