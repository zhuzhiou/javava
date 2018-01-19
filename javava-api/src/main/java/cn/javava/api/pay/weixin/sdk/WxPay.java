package cn.javava.api.pay.weixin.sdk;

import cn.javava.api.pay.weixin.config.ApiConfig;
import cn.javava.api.pay.weixin.constant.WxConstants;
import cn.javava.api.pay.weixin.util.WxPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxPay {

    private static final Logger logger = LoggerFactory.getLogger(WxPay.class);

    private WxConstants.SignType signType = WxConstants.SignType.MD5;

    @Autowired
    private WxPayRequest wxPayRequest;

    @Autowired
    private ApiConfig apiConfig;

    /**
     * 作用：统一下单<br>
     * 场景：公共号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(Map<String, String> reqData) throws Exception {
        return this.unifiedOrder(reqData, apiConfig.getHttpConnectTimeout(), apiConfig.getHttpReadTimeout());
    }

    public Map<String, String> unifiedOrder(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String respXml = this.requestWithoutCert(apiConfig.getUnifiedOrderUrl(), reqData, connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    private Map<String, String> processResponseXml(String xmlStr) throws Exception {
        String RETURN_CODE = "return_code";
        String return_code;
        Map<String, String> respData = WxPayUtil.xmlToMap(xmlStr);
        if (respData.containsKey(RETURN_CODE)) {
            return_code = respData.get(RETURN_CODE);
        }else {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }
        if (return_code.equals(WxConstants.FAIL)) {
            return respData;
        }else if (return_code.equals(WxConstants.SUCCESS)) {
            if (this.isResponseSignatureValid(respData)) {
                return respData;
            }else {
                throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
            }
        }else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlStr));
        }
    }
    /**
     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
        // 返回数据的签名方式和请求中给定的签名方式是一致的
        return WxPayUtil.isSignatureValid(reqData, apiConfig.getKey(), this.signType);
    }

    /**
     * 不需要证书的请求
     * @param url String
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs 超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public String requestWithoutCert(String url, Map<String, String> reqData,
                                     int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String reqBody = WxPayUtil.mapToXml(reqData);
        return this.wxPayRequest.requestWithoutCert(url, reqBody, connectTimeoutMs, readTimeoutMs);
    }

}
