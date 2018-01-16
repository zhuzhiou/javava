package com.javava.thirdparty.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.javava.thirdparty.weixin.constant.WxPayConstants;
import com.javava.thirdparty.weixin.entity.FlowRecord;
import com.javava.thirdparty.weixin.entity.Goods;
import com.javava.thirdparty.weixin.entity.Prepay;
import com.javava.thirdparty.weixin.sdk.RequestDataBuilder;
import com.javava.thirdparty.weixin.sdk.WxPay;
import com.javava.thirdparty.weixin.service.FlowRecordService;
import com.javava.thirdparty.weixin.service.GoodsService;
import com.javava.thirdparty.weixin.service.PrepayService;
import com.javava.thirdparty.weixin.service.WalletService;
import com.javava.thirdparty.weixin.util.WxPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlrllr on 2018/1/4.
 */
@RestController
@RequestMapping("notify")
public class WxNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(WxNotifyController.class);

    @Autowired
    private WxPay wxPay;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PrepayService prepayService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private FlowRecordService flowRecordService;
    @Autowired
    private RequestDataBuilder requestDataBuilder;

    /**
     * 支付结果通知
     */
    @RequestMapping("/payedInfo")
    public void notifyPayedInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        result.put("return_code", WxPayConstants.SUCCESS);
        result.put("return_msg", "支付成功");
        //获取参数
        Map<String, String> params = getParameter(request);
        try (PrintWriter writer = response.getWriter()) {
            String userId = "";
            if (wxPay.isPayResultNotifySignatureValid(params)) {
                if (WxPayUtil.isTure(WxPayConstants.SUCCESS, result, "return_code", "result_code")) {
                    //校验支付金额
                    String outTradeNo = params.get("out_trade_no");
                    Prepay prepay = prepayService.findByOutTradeNo(outTradeNo);
                    if (prepay != null) {
                        userId = prepay.getUserId();
                        if (prepay.getTotalFee().intValue() == Integer.parseInt(params.get("total_fee"))) {
                            //构建用户钱包信息
                            walletService.refreshWallet(params, userId);
                        } else {
                            logger.error("支付异常，支付金额与订单金额不一致,支付金额：{}，订单金额：{}", params.get("total_fee"), prepay.getTotalFee());
                        }
                    } else {
                        logger.error("支付异常，找不到预付订单.....");
                    }
                }
            }
            flowRecordService.save(params, userId);
            String returnStr = WxPayUtil.mapToXml(result);
            logger.info("处理业务结束，返回支付通知处理结果：{}", returnStr);
            writer.append(returnStr);
            writer.flush();
        } catch (Exception e) {
            logger.error("通知结果后续处理异常....", e);
        }

    }

    /**
     * 客户端请求获取订单状态
     * @param tradeNo
     * @return
     */
    @RequestMapping("/payStatus/{tradeNo}")
    public JSONObject respStatus(@PathVariable String tradeNo){
        JSONObject resp = new JSONObject();
        resp.put("result_code",WxPayConstants.DEALING);
        FlowRecord record = flowRecordService.findByOutTradeNo(tradeNo);
        if(record != null){
            resp.put("result_code",record.getResultCode());
        }
        return resp;
    }


    /**
     * 普通支付模式一
     * 回调商户支付URL
     *
     * @param request
     * @param response
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        Map<String, String> params = getParameter(request);
        //签名校验
        try {
            String[] str = params.get("product_id").split(WxPayConstants.SPLIT_SYMBOL);
            Goods goods = goodsService.findById(params.get(str[1]));
            if (goods != null) {
                Map<String, String> data = requestDataBuilder.buildUnifiedOrderParam(goods.getPrice(), str[0], goods.getId());
                Map<String, String> result = wxPay.unifiedOrder(data);
                if (WxPayUtil.isTure(WxPayConstants.SUCCESS, result, "return_code", "result_code")) {
                    Prepay prepay = new Prepay();
                    prepay.setDeviceInfo(str[0]);
                    prepay.setOutTradeNo(data.get("out_trade_no"));
                    prepay.setTotalFee(goods.getPrice());
                    prepay.setPrepayId(result.get("prepay_id"));
                    prepayService.save(prepay);
                    PrintWriter writer = response.getWriter();
                    writer.append(WxPayUtil.mapToXml(result));
                    writer.flush();
                }
            }
        } catch (Exception e) {
            logger.error("回调支付异常....", e);
        }
    }


    private Map<String, String> getParameter(HttpServletRequest request) {
        StringBuffer xmlStr = new StringBuffer();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }
            logger.info("请求参数：" + xmlStr.toString());
            return WxPayUtil.xmlToMap(xmlStr.toString());
        } catch (Exception e) {
            logger.error("获取请求参数异常....", e);
        }
        return null;
    }
}
