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
        flowRecord.setMoney(Integer.parseInt(data.get("total_fee")));
        flowRecord.setOperateTime(new Date());
        flowRecord.setOutTradeNo(data.get("out_trade_no"));
        flowRecord.setRemark(JSONObject.toJSONString(data));
        if (WxPayUtil.isTure(WxConstants.SUCCESS, data, "return_code", "result_code")){
            flowRecord.setResultCode(WxConstants.SUCCESS);
        }else{
            flowRecord.setResultCode(WxConstants.FAIL);
        }
        flowRecord.setTransactionId(data.get("transaction_id"));
        flowRecord.setType(WxConstants.OPERATE_TYPE_RECHARGE);
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
