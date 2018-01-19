package cn.javava.thirdparty.weixin.sdk;

import cn.javava.thirdparty.weixin.constant.WxPayConstants;
import cn.javava.thirdparty.weixin.util.WxPayUtil;
import cn.javava.thirdparty.weixin.config.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlrllr on 2018/1/15.
 */
@Component
public class RequestDataBuilder {

    private static final Logger logger = LoggerFactory.getLogger(RequestDataBuilder.class);

    @Autowired
    private ApiConfig apiConfig;

    public Map<String, String> buildUnifiedOrderParam(Integer money, String clientId, String productId) {
        Map<String, String> data = new HashMap<>();
        data.put("product_id", productId);
        return buildUnifiedOrderParam(data, money, clientId);
    }

    /**
     * 构建统一下单参数
     *
     * @return
     */
    public Map<String, String> buildUnifiedOrderParam(Integer money, String clientId) {
        return buildUnifiedOrderParam(null, money, clientId);
    }

    /**
     * 构建统一下单参数
     *
     * @return
     */
    public Map<String, String> buildUnifiedOrderParam(Map<String, String> data, Integer money, String clientId) {
        if (data == null) {
            data = new HashMap<>();
        }
        try {
            data.put("body", apiConfig.getGoodsBody());
            data.put("total_fee", money.toString());
            data.put("spbill_create_ip", apiConfig.getIp());
            data.put("out_trade_no", WxPayUtil.generateTradeNo());
            data.put("notify_url", apiConfig.getNotifyUrl());
            data.put("trade_type", apiConfig.getTradeType());
            data.put("device_info", clientId);
            data.put("fee_type", "CNY");
            return fillRequestData(data);
        } catch (Exception e) {
            logger.error("=========组装请求数据异常======", e);
        }
        return data;
    }

    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param data
     * @return
     * @throws Exception
     */
    public Map<String, String> fillRequestData(Map<String, String> data) throws Exception {
        data.put("appid", apiConfig.getAppId());
        data.put("mch_id", apiConfig.getMchId());
        data.put("nonce_str", WxPayUtil.generateUUID());
        WxPayConstants.SignType signType = WxPayConstants.SignType.MD5;
        data.put("sign_type", WxPayConstants.MD5);
        if (WxPayConstants.HMACSHA256.equals(apiConfig.getSignType())) {
            signType = WxPayConstants.SignType.HMACSHA256;
            data.put("sign_type", WxPayConstants.HMACSHA256);
        }
        data.put("sign", WxPayUtil.generateSignature(data, apiConfig.getKey(), signType));
        return data;
    }
}
