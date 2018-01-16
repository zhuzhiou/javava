package com.javava.thirdparty.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javava.thirdparty.weixin.entity.FlowRecord;
import com.javava.thirdparty.weixin.entity.Wallet;
import com.javava.thirdparty.weixin.repository.FlowRecordRepository;
import com.javava.thirdparty.weixin.repository.WalletRepository;
import com.javava.thirdparty.weixin.service.FlowRecordService;
import com.javava.thirdparty.weixin.util.WxPayUtil;
import com.javava.thirdparty.weixin.constant.WxPayConstants;
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
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public FlowRecord save(Map<String,String> data, String userId) {
        FlowRecord flowRecord = new FlowRecord();
        flowRecord.setId(WxPayUtil.generateUUID());
        flowRecord.setOpenid(data.get("openid"));
        flowRecord.setUserId(userId);
        flowRecord.setBankType(data.get("bank_type"));
        flowRecord.setDeviceInfo(data.get("device_info"));
        flowRecord.setMoney(Double.parseDouble(data.get("total_fee")));
        flowRecord.setOperateTime(new Date());
        flowRecord.setOutTradeNo(data.get("out_trade_no"));
        flowRecord.setRemark(JSONObject.toJSONString(data));
        if (WxPayUtil.isTure(WxPayConstants.SUCCESS, data, "return_code", "result_code")){
            flowRecord.setResultCode(WxPayConstants.SUCCESS);
        }else{
            flowRecord.setResultCode(WxPayConstants.FAIL);
        }
        flowRecord.setTransactionId(data.get("transaction_id"));
        flowRecord.setType(WxPayConstants.OPERATE_TYPE_RECHARGE);
        //通过openId去查找钱包ID
        Wallet wallet = walletRepository.findByOpenid(flowRecord.getOpenid());
        if(wallet != null){
            flowRecord.setWalletId(wallet.getId());
        }
        return flowRecordRepository.saveAndFlush(flowRecord);
    }

    @Override
    public FlowRecord findByOutTradeNo(String outTradeNo) {
       return flowRecordRepository.findByOutTradeNo(outTradeNo);
    }
}
