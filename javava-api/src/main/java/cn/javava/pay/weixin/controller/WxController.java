package cn.javava.pay.weixin.controller;

import cn.javava.pay.weixin.config.ApiConfig;
import cn.javava.pay.weixin.constant.WxConstants;
import cn.javava.pay.weixin.entity.FlowRecord;
import cn.javava.pay.weixin.entity.Prepay;
import cn.javava.pay.weixin.sdk.RequestDataBuilder;
import cn.javava.pay.weixin.sdk.WxPay;
import cn.javava.pay.weixin.service.FlowRecordService;
import cn.javava.pay.weixin.service.PrepayService;
import cn.javava.pay.weixin.util.NetWorkUtils;
import cn.javava.pay.weixin.util.WxPayUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlrllr on 2018/3/12.
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
    @Autowired
    private ApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    private static final String DEFAULT_CLIENT_ID ="0000001";

    @RequestMapping(value="/generateQRCode", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject generatePrepayUrl(HttpServletRequest request,@RequestParam String userId, @RequestParam Integer money) throws Exception {
        JSONObject resp = new JSONObject();
        String ip = NetWorkUtils.getIpAddr(request);
        Map<String,String> result = generatePrepayUrl(ip,userId, DEFAULT_CLIENT_ID,money,null);
        if(WxPayUtil.isTure(WxConstants.SUCCESS,result,"return_code","result_code")){
            resp.put("code", 0);
            resp.put("message", "成功");
            resp.put(WxConstants.RESPONSE_CODE_URL, result.get("code_url"));
            resp.put(WxConstants.RESPONSE_TRADE_NO, result.get("out_trade_no"));
        }else{
            resp.put("code", 1);
            resp.put("message", "生成支付二维码失败");
        }
        return resp;
    }

    @PostMapping(value="/prePay")
    public Map<String,String> prepay(HttpServletRequest request,double money) throws Exception {
        String openId="oHqvmw_xbl0ouyP_sLK8gDZAodPE";
        String ip = NetWorkUtils.getIpAddr(request);
        Map<String,String> resp = generatePrepayUrl(ip,openId,DEFAULT_CLIENT_ID,(int)(money*100),openId);
        Map<String,String> param = new HashMap<>();
        param.put("appId",resp.get("appid"));
        param.put("timeStamp",System.currentTimeMillis()/1000+"");
        param.put("nonceStr",WxPayUtil.generateUUID());
        param.put("package","prepay_id="+resp.get("prepay_id"));
        param.put("signType",WxConstants.MD5);
        WxConstants.SignType signType = WxConstants.SignType.MD5;
        if (WxConstants.HMACSHA256.equals(apiConfig.getSignType())) {
            signType = WxConstants.SignType.HMACSHA256;
            param.put("signType", WxConstants.HMACSHA256);
        }
        param.put("paySign", WxPayUtil.generateSignature(param, apiConfig.getKey(), signType));
        if(WxPayUtil.isTure(WxConstants.SUCCESS,resp,"return_code","result_code")){
            param.put("code", "0");
        }
        logger.info("下单成功調起支付参数：{}", JSONObject.toJSONString(param));
        return param;
    }

    private Map<String,String> generatePrepayUrl(String ip,String userId, String clientId, Integer money,String openId) throws Exception {
        Map<String, String> data = requestDataBuilder.buildUnifiedOrderParam(ip,money,clientId,openId);
        logger.info("统一下单参数：{}", JSONObject.toJSONString(data));
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
            result.put("out_trade_no",prepay.getOutTradeNo());
            return result;
        }
        return result;
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
            resp.put("result",record);
        } else {
            resp.put("code", 1);
            resp.put("message", "订单号不存在");
        }
        return resp;
    }
}
