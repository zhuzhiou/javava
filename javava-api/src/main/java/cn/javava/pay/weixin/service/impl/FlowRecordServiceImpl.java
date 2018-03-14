package cn.javava.pay.weixin.service.impl;

import cn.javava.pay.weixin.constant.WxConstants;
import cn.javava.pay.weixin.entity.FlowRecord;
import cn.javava.pay.weixin.entity.Wallet;
import cn.javava.pay.weixin.repository.FlowRecordRepository;
import cn.javava.pay.weixin.repository.WalletRepository;
import cn.javava.pay.weixin.service.FlowRecordService;
import cn.javava.pay.weixin.util.WxPayUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by wlrllr on 2018/1/15.
 */
@Service
public class FlowRecordServiceImpl implements FlowRecordService {
    @Autowired
    private FlowRecordRepository flowRecordRepository;
    @Override
    public FlowRecord findByOutTradeNo(String outTradeNo) {
       return flowRecordRepository.findByOutTradeNo(outTradeNo);
    }
}
