package cn.javava.pay.weixin.repository;

import cn.javava.pay.weixin.entity.FlowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wlrllr on 2018/1/12.
 */
public interface FlowRecordRepository extends JpaRepository<FlowRecord,String> {

    FlowRecord findByOutTradeNo(String outTradeNo);
}
