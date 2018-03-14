package cn.javava.pay.weixin.sdk;

import cn.javava.pay.weixin.config.ApiConfig;
import cn.javava.pay.weixin.constant.WxConstants;
import cn.javava.pay.weixin.util.WxPayUtil;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 构建统一下单参数
     *
     * @return
     */
    public Map<String, String> buildUnifiedOrderParam(String ip, Integer money, String clientId, String openId) {
        return buildUnifiedOrderParam(null, ip, money, clientId, openId);
    }

    /**
     * 构建统一下单参数
     *
     * @return
     */
    public Map<String, String> buildUnifiedOrderParam(Map<String, String> data, String ip, Integer money, String clientId, String openId) {
        if (data == null) {
            data = new HashMap<>();
        }
        try {
            data.put("body", apiConfig.getGoodsBody());
            data.put("total_fee", money.toString());
            if (StringUtils.isNotBlank(openId))
                data.put("openid", openId);
            if (StringUtils.isBlank(ip)) {
                data.put("spbill_create_ip", apiConfig.getIp());
            } else {
                data.put("spbill_create_ip", ip);

            }
            data.put("out_trade_no", WxPayUtil.generateTradeNo());
            data.put("notify_url", apiConfig.getNotifyUrl());
            data.put("trade_type", apiConfig.getTradeType());
            if (StringUtils.isNotBlank(clientId)) {
                data.put("device_info", clientId);
            } else {
                data.put("device_info", "WEB");
            }
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
        WxConstants.SignType signType = WxConstants.SignType.MD5;
        data.put("sign_type", WxConstants.MD5);
        if (WxConstants.HMACSHA256.equals(apiConfig.getSignType())) {
            signType = WxConstants.SignType.HMACSHA256;
            data.put("sign_type", WxConstants.HMACSHA256);
        }
        data.put("sign", WxPayUtil.generateSignature(data, apiConfig.getKey(), signType));
        return data;
    }
}
