package cn.javava.shelf.repository;

import cn.javava.shelf.entity.ShelfBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Repository
public interface ShelfBoxRepository extends JpaRepository<ShelfBox, Long> {
    List<ShelfBox> findByShelfId(Long shelfId);
}
