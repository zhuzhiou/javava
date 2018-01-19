package cn.javava.pay.service;


import cn.javava.pay.entity.FlowRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface FlowRecordService {

    List<FlowRecord> list(FlowRecord record, Date beginTime, Date endTime);
}
