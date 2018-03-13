package cn.javava.weixin.pay.controller;

import cn.javava.weixin.pay.config.ApiPayConfig;
import cn.javava.weixin.pay.constant.WxPayConstants;
import cn.javava.weixin.pay.entity.Prepay;
import cn.javava.weixin.pay.sdk.RequestDataBuilder;
import cn.javava.weixin.pay.sdk.WxPay;
import cn.javava.weixin.pay.service.PrepayService;
import cn.javava.weixin.pay.util.NetWorkUtils;
import cn.javava.weixin.pay.util.WxPayUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlrllr on 2018/3/12.
 */
@RequestMapping("/trades")
@Controller()
public class WxController {

    @Autowired
    private WxPay wxPay;
    @Autowired
    private PrepayService prepayService;
    @Autowired
    private RequestDataBuilder requestDataBuilder;
    @Autowired
    private ApiPayConfig apiPayConfig;

    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    private static final String DEFAULT_CLIENT_ID ="0000001";

    @RequestMapping(value="/generateQRCode", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject generatePrepayUrl(HttpServletRequest request, @RequestParam String userId, @RequestParam Integer money) throws Exception {
        JSONObject resp = new JSONObject();
        String ip = NetWorkUtils.getIpAddress(request);
        Map<String,String> result = generatePrepayUrl(ip,userId, DEFAULT_CLIENT_ID,money,null);
        if(WxPayUtil.isTrue(WxPayConstants.SUCCESS,result,"return_code","result_code")){
            resp.put("code", 0);
            resp.put("message", "成功");
            resp.put(WxPayConstants.RESPONSE_CODE_URL, result.get("code_url"));
            resp.put(WxPayConstants.RESPONSE_TRADE_NO, result.get("out_trade_no"));
        }else{
            resp.put("code", 1);
            resp.put("message", "生成支付二维码失败");
        }
        return resp;
    }

    @GetMapping("")
    public String index(){
        return "pay";
    }

    @PostMapping(value="/prePay")
    @ResponseBody
    public Map<String,String> prepay(HttpServletRequest request,double money) throws Exception {
        //获取用户openId
        String openId="oHqvmw_xbl0ouyP_sLK8gDZAodPE";
        String ip = NetWorkUtils.getIpAddress(request);
        Map<String,String> resp = generatePrepayUrl(ip,openId,DEFAULT_CLIENT_ID,(int)(money*100),openId);
        Map<String,String> param = new HashMap<>();
        param.put("appId",resp.get("appid"));
        param.put("timeStamp",System.currentTimeMillis()/1000+"");
        param.put("nonceStr",WxPayUtil.generateUUID());
        param.put("package","prepay_id="+resp.get("prepay_id"));
        param.put("signType",WxPayConstants.MD5);
        WxPayConstants.SignType signType = WxPayConstants.SignType.MD5;
        if (WxPayConstants.HMACSHA256.equals(apiPayConfig.getSignType())) {
            signType = WxPayConstants.SignType.HMACSHA256;
            param.put("signType", WxPayConstants.HMACSHA256);
        }
        param.put("paySign", WxPayUtil.generateSignature(param, apiPayConfig.getKey(), signType));
        if(WxPayUtil.isTrue(WxPayConstants.SUCCESS,resp,"return_code","result_code")){
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
        if(WxPayUtil.isTrue(WxPayConstants.SUCCESS,result,"return_code","result_code")){
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
}
