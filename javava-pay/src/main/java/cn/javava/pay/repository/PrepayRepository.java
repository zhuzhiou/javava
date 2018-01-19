package cn.javava.pay.repository;


import cn.javava.pay.entity.Prepay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wlrllr on 2018/1/12.
 */
public interface PrepayRepository extends JpaRepository<Prepay,String> {
}
