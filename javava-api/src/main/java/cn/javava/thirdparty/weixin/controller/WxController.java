package cn.javava.thirdparty.weixin.controller;

import cn.javava.api.commons.controller.BaseApiController;
import cn.javava.thirdparty.weixin.constant.WxConstants;
import cn.javava.thirdparty.weixin.entity.FlowRecord;
import cn.javava.thirdparty.weixin.entity.Prepay;
import cn.javava.thirdparty.weixin.sdk.RequestDataBuilder;
import cn.javava.thirdparty.weixin.sdk.WxPay;
import cn.javava.thirdparty.weixin.service.FlowRecordService;
import cn.javava.thirdparty.weixin.service.PrepayService;
import cn.javava.thirdparty.weixin.util.WxPayUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/11.
 */
@RequestMapping("/pay")
@RestController()
public class WxController extends BaseApiController {

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

    @PostMapping(value="/generateQRCode")
    public JSONObject generatePrepayUrl(String userId,Integer money){
        return generatePrepayUrl(userId,DEFAULT_CLIENTID,money);
    }

    private JSONObject generatePrepayUrl(String userId,String clientId,Integer money){
        JSONObject resp = new JSONObject();
        try {
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
                resp.put(WxConstants.RESPONSE_CODE,result.get("code_url"));
                resp.put(WxConstants.RESPONSE_TRADE_NO,prepay.getOutTradeNo());
                return resp;
            }
        } catch (Exception e) {
            logger.error("generatePrepayUrl error",e);
        }
        return resp;
    }

    /**
     * 客户端请求获取订单状态
     * @param tradeNo
     * @return
     */
    @GetMapping(value = "/payStatus/{tradeNo}")
    public JSONObject respStatus(@PathVariable String tradeNo){
        JSONObject resp = new JSONObject();
        resp.put("resultCode", WxConstants.DEALING);
        FlowRecord record = flowRecordService.findByOutTradeNo(tradeNo);
        if(record != null){
            resp.put("resultCode",record.getResultCode());
        }
        return resp;
    }
}
