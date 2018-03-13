package cn.javava.pay.weixin.service;

import cn.javava.pay.weixin.entity.FlowRecord;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface FlowRecordService {

    FlowRecord findByOutTradeNo(String outTradeNo);
}
