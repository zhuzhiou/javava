package cn.javava.weixin.pay.sdk;

import cn.javava.weixin.pay.config.ApiConfig;
import cn.javava.weixin.pay.constant.WxPayConstants;
import cn.javava.weixin.pay.util.WxPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxPay {

    private static final Logger logger = LoggerFactory.getLogger(WxPay.class);

    private WxPayConstants.SignType signType = WxPayConstants.SignType.MD5;

    @Autowired
    private WxPayRequest wxPayRequest;

    @Autowired
    private ApiConfig apiConfig;


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
     * 判断支付结果通知中的sign是否有效
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public boolean isPayResultNotifySignatureValid(Map<String, String> reqData) throws Exception {
        String signTypeInData = reqData.get(WxPayConstants.FIELD_SIGN_TYPE);
        WxPayConstants.SignType signType;
        if (signTypeInData == null) {
            signType = WxPayConstants.SignType.MD5;
        }
        else {
            signTypeInData = signTypeInData.trim();
            if (signTypeInData.length() == 0) {
                signType = WxPayConstants.SignType.MD5;
            }
            else if (WxPayConstants.MD5.equals(signTypeInData)) {
                signType = WxPayConstants.SignType.MD5;
            }
            else if (WxPayConstants.HMACSHA256.equals(signTypeInData)) {
                signType = WxPayConstants.SignType.HMACSHA256;
            }
            else {
                throw new Exception(String.format("Unsupported sign_type: %s", signTypeInData));
            }
        }
        return WxPayUtil.isSignatureValid(reqData, apiConfig.getKey(), signType);
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
        String resp = this.wxPayRequest.requestWithoutCert(url, reqBody, connectTimeoutMs, readTimeoutMs);
        return resp;
    }


    /**
     * 需要证书的请求
     * @param urlSuffix String
     * @param reqData 向wxpay post的请求数据  Map
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs 超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public String requestWithCert(String urlSuffix, Map<String, String> reqData,
                                  int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String reqBody = WxPayUtil.mapToXml(reqData);
        String resp = this.wxPayRequest.requestWithCert(urlSuffix, reqBody, connectTimeoutMs, readTimeoutMs,null);
        return resp;
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    private Map<String, String> processResponseXml(String xmlStr) throws Exception {
        Map<String, String> respData = WxPayUtil.xmlToMap(xmlStr);
        String returnCode = respData.get("return_code");
        if (StringUtils.isBlank(returnCode)) {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }
        if (returnCode.equals(WxPayConstants.FAIL)) {
            return respData;
        }else if (returnCode.equals(WxPayConstants.SUCCESS)) {
           if (this.isResponseSignatureValid(respData)) {
               return respData;
           }else {
               throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
           }
        }else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", returnCode, xmlStr));
        }
    }

    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> report(Map<String, String> reqData) throws Exception {
        return this.report(reqData, apiConfig.getHttpConnectTimeout(), apiConfig.getHttpReadTimeout());
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> report(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String respXml = this.requestWithoutCert(apiConfig.getReport(), reqData, connectTimeoutMs, readTimeoutMs);
        return WxPayUtil.xmlToMap(respXml);
    }


    /**
     * 作用：转换短链接<br>
     * 场景：刷卡支付、扫码支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> shortUrl(Map<String, String> reqData) throws Exception {
        return this.shortUrl(reqData, apiConfig.getHttpConnectTimeout(), apiConfig.getHttpReadTimeout());
    }


    /**
     * 作用：转换短链接<br>
     * 场景：刷卡支付、扫码支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> shortUrl(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String respXml = this.requestWithoutCert(apiConfig.getShortUrl(), reqData, connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }

}
