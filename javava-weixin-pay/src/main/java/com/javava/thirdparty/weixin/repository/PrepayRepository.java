package com.javava.thirdparty.weixin.repository;

import com.javava.thirdparty.weixin.entity.Prepay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wlrllr on 2018/1/12.
 */
public interface PrepayRepository extends JpaRepository<Prepay,String> {
}
