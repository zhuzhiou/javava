package com.javava.thirdparty.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.javava.thirdparty.weixin.config.ApiConfig;
import com.javava.thirdparty.weixin.constant.WxPayConstants;
import com.javava.thirdparty.weixin.entity.Goods;
import com.javava.thirdparty.weixin.entity.Prepay;
import com.javava.thirdparty.weixin.repository.GoodsRepository;
import com.javava.thirdparty.weixin.sdk.RequestDataBuilder;
import com.javava.thirdparty.weixin.sdk.WxPay;
import com.javava.thirdparty.weixin.service.GoodsService;
import com.javava.thirdparty.weixin.service.PrepayService;
import com.javava.thirdparty.weixin.util.WxPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlrllr on 2018/1/11.
 */
@RestController
@RequestMapping("code")
public class WxCodeController {

    @Autowired
    private ApiConfig apiConfig;
    @Autowired
    private WxPay wxPay;
    @Autowired
    private PrepayService prepayService;
    @Autowired
    private RequestDataBuilder requestDataBuilder;
    @Autowired
    private GoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(WxCodeController.class);

    private static final String PAY_URL="weixin://wxpay/bizpayurl";

    /**
     * 生成商品二维码Url接口
     * 1.用户扫码
     * 2.微信通过回调商户平台设置的回调URL，调用后台下单，生成预付单，返回给商户平台
     * 3.微信商户平台，“通知”用户支付
     * 4.支付成功后，商户平台调用后台通知URL
     * 5.后台校验成功后，告知商户平台
     * 6.商户平台通知用户后台，支付成功
     * @return
     */
    public String generatePayUrl(String clientId,String productId){
       //获取商品Id
        Map<String,String> reqData = new HashMap<>();
        reqData.put("appid",apiConfig.getAppId());
        reqData.put("mch_id",apiConfig.getMchId());
        reqData.put("time_stamp",String.valueOf(System.currentTimeMillis()));
        reqData.put("nonce_str", WxPayUtil.generateNonceStr());
        reqData.put("product_id",clientId+ WxPayConstants.SPLIT_SYMBOL+productId);
        try{
            reqData.put("sign", WxPayUtil.generateSignature(reqData, apiConfig.getKey(), WxPayConstants.SignType.MD5));
            return shortUrl(PAY_URL+"?"+ WxPayUtil.mapToStr(reqData));
        }catch (Exception e){
            logger.error("generatePayUrl error",e);
        }
        return "";
    }

    /**
     * 生成预付订单，并返回支付二维码URL（有效期2小时）
     * 1.微信商户平台，“通知”用户支付
     * 2.支付成功后，商户平台调用后台通知URL
     * 3.后台校验成功后，告知商户平台
     * 4.商户平台通知用户后台，支付成功
     * @param clientId
     * @param productId
     * @return
     */
    public JSONObject generatePrepayUrl(String clientId,String productId){
        JSONObject resp = new JSONObject();
        try {
            Goods goods = goodsService.findById(productId);
            if(goods != null){
                Map<String, String> data = requestDataBuilder.buildUnifiedOrderParam(goods.getPrice(),clientId,productId);
                Map<String,String> result = wxPay.unifiedOrder(data);
                logger.info("生成实时二维码接口(商品ID)，统一下单返回结果：{}", JSONObject.toJSONString(result));
                if(WxPayUtil.isTure(WxPayConstants.SUCCESS,result,"return_code","result_code")){
                    Prepay prepay = new Prepay();
                    prepay.setDeviceInfo(clientId);
                    prepay.setOutTradeNo(data.get("out_trade_no"));
                    prepay.setTotalFee(goods.getPrice());
                    prepay.setPrepayId(result.get("prepay_id"));
                    prepayService.save(prepay);
                    resp.put(WxPayConstants.RESPONSE_CODE,result.get("code_url"));
                    resp.put(WxPayConstants.RESPONSE_TRADE_NO,prepay.getOutTradeNo());
                    return resp;
                }
            }

        } catch (Exception e) {
            logger.error("generatePrepayUrl error",e);
        }
        return resp;
    }

    @RequestMapping(value="/generate")
    public JSONObject generatePrepayUrl(String userId,String clientId,Integer money){
        JSONObject resp = new JSONObject();
        try {
            Map<String, String> data = requestDataBuilder.buildUnifiedOrderParam(money,clientId);
            Map<String,String> result = wxPay.unifiedOrder(data);
            logger.info("生成实时二维码接口，统一下单返回结果：{}", JSONObject.toJSONString(result));
            if(WxPayUtil.isTure(WxPayConstants.SUCCESS,result,"return_code","result_code")){
                Prepay prepay = new Prepay();
                prepay.setDeviceInfo(clientId);
                prepay.setOutTradeNo(data.get("out_trade_no"));
                prepay.setTotalFee(money);
                prepay.setPrepayId(result.get("prepay_id"));
                prepay.setUserId(userId);
                prepayService.save(prepay);
                resp.put(WxPayConstants.RESPONSE_CODE,result.get("code_url"));
                resp.put(WxPayConstants.RESPONSE_TRADE_NO,prepay.getOutTradeNo());
                return resp;
            }
        } catch (Exception e) {
            logger.error("generatePrepayUrl error",e);
        }
        return resp;
    }

    private String shortUrl(String url){
        logger.info("长连接转短连接，long url：{}",url);
        Map<String,String> reqData = new HashMap<>();
        reqData.put("appid",apiConfig.getAppId());
        reqData.put("mch_id",apiConfig.getMchId());
        reqData.put("nonce_str", WxPayUtil.generateNonceStr());
        reqData.put("long_url",url);
        try{
            reqData.put("sign", WxPayUtil.generateSignature(reqData, apiConfig.getKey(), WxPayConstants.SignType.MD5));
            Map<String,String> result = wxPay.shortUrl(reqData);
            if(WxPayUtil.isTure(WxPayConstants.SUCCESS,result,"return_code","result_code")){
                return  result.get("short_url");
            }
        }catch (Exception e){
            logger.error("coverShortUrl error",e);
        }
        return "";
    }

}
