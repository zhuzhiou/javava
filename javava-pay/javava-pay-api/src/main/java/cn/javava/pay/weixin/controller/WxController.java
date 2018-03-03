package cn.javava.pay.weixin.controller;

import cn.javava.pay.weixin.constant.WxConstants;
import cn.javava.pay.weixin.entity.FlowRecord;
import cn.javava.pay.weixin.entity.Prepay;
import cn.javava.pay.weixin.sdk.RequestDataBuilder;
import cn.javava.pay.weixin.sdk.WxPay;
import cn.javava.pay.weixin.service.FlowRecordService;
import cn.javava.pay.weixin.service.PrepayService;
import cn.javava.pay.weixin.util.WxPayUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/11.
 */
@RequestMapping("/trades")
@RestController()
public class WxController {

    @Autowired
    private WxPay wxPay;
    @Autowired
    private PrepayService prepayService;
    @Autowired
    private FlowRecordService flowRecordService;
    @Autowired
    private RequestDataBuilder requestDataBuilder;

    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    private static final String DEFAULT_CLIENTID="0000001";

    @RequestMapping(value="/generateQRCode", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject generatePrepayUrl(@RequestParam String userId, @RequestParam Integer money) throws Exception {
        return generatePrepayUrl(userId,DEFAULT_CLIENTID,money);
    }

    private JSONObject generatePrepayUrl(String userId, String clientId, Integer money) throws Exception {
        JSONObject resp = new JSONObject();
        Map<String, String> data = requestDataBuilder.buildUnifiedOrderParam(money,clientId);
        Map<String,String> result = wxPay.unifiedOrder(data);
        logger.info("生成实时二维码接口，统一下单返回结果：{}", JSONObject.toJSONString(result));
        if(WxPayUtil.isTure(WxConstants.SUCCESS,result,"return_code","result_code")){
            Prepay prepay = new Prepay();
            prepay.setDeviceInfo(clientId);
            prepay.setOutTradeNo(data.get("out_trade_no"));
            prepay.setTotalFee(money);
            prepay.setPrepayId(result.get("prepay_id"));
            prepay.setUserId(userId);
            prepayService.save(prepay);
            resp.put("code", 0);
            resp.put("message", "成功");
            resp.put(WxConstants.RESPONSE_CODE_URL, result.get("code_url"));
            resp.put(WxConstants.RESPONSE_TRADE_NO, prepay.getOutTradeNo());
            return resp;
        } else {
            resp.put("code", 1);
            resp.put("message", "生成支付二维码失败");
        }
        return resp;
    }

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
            resp.put("result_code",record.getResultCode());
        } else {
            resp.put("code", 1);
            resp.put("message", "订单号不存在");
        }
        return resp;
    }
}
