package cn.javava.weixin.pay.service;

import cn.javava.weixin.pay.entity.FlowRecord;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface FlowRecordService {

    FlowRecord save(Map<String, String> data, String userId);

    FlowRecord findByOutTradeNo(String outTradeNo);
}
