package cn.javava.pay.weixin.controller;

import cn.javava.pay.weixin.entity.FlowRecord;
import cn.javava.pay.weixin.service.FlowRecordService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wlrllr on 2018/3/12.
 */
@RequestMapping("/trades")
@RestController()
public class WxController {

    @Autowired
    private FlowRecordService flowRecordService;

    /**
     * 客户端请求获取订单状态
     * @param tradeNo
     * @return
     */
    @GetMapping(value = "/{tradeNo}")
    public JSONObject respStatus(@PathVariable String tradeNo){
        JSONObject resp = new JSONObject();
        FlowRecord record = flowRecordService.findByOutTradeNo(tradeNo);
        if(record != null){
            resp.put("code", 0);
            resp.put("message", "成功");
            resp.put("result",record);
        } else {
            resp.put("code", 1);
            resp.put("message", "订单号不存在");
        }
        return resp;
    }
}
