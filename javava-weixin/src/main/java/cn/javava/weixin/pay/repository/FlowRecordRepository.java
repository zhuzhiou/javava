package cn.javava.weixin.pay.repository;

import cn.javava.weixin.pay.entity.FlowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wlrllr on 2018/1/12.
 */
public interface FlowRecordRepository extends JpaRepository<FlowRecord,String> {

    FlowRecord findByOutTradeNo(String outTradeNo);
}
