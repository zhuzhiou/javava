package cn.javava.thirdparty.weixin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@PropertySource("classpath:application.yaml")
@Configuration
public class ApiConfig implements Serializable {

    @Value("${wx.pay.unified-order-url}")
    private String unifiedOrderUrl;
    @Value("${wx.pay.query-order-url}")
    private String queryOrderUrl;
    @Value("${wx.pay.close-order-url}")
    private String closeOrderUrl;
    @Value("${wx.pay.refund-url}")
    private String refundUrl;
    @Value("${wx.pay.refund-query-url}")
    private String refundQueryUrl;
    @Value("${wx.pay.micro-pay}")
    private String microPay;
    @Value("${wx.pay.reverse}")
    private String reverse;
    @Value("${wx.pay.download-bill}")
    private String downloadBill;
    @Value("${wx.pay.report}")
    private String report;
    @Value("${wx.pay.auth-code-to-openid}")
    private String authCodeToOpenid;

    @Value("${wx.pay.short-url}")
    private String shortUrl;
    @Value("${wx.pay.trade-type}")
    private String tradeType;
    @Value("${wx.pay.goods-body}")
    private String goodsBody;

    @Value("${wx.api.app-id}")
    private String appId;
    @Value("${wx.api.mch-id}")
    private String mchId;
    @Value("${wx.api.key}")
    private String key;
    @Value("${wx.api.notify-url}")
    private String notifyUrl;
    @Value("${wx.api.sign-type}")
    private String signType;

    @Value("${http.connect-timeout}")
    private int httpConnectTimeout;
    @Value("${http.read-timeout}")
    private int httpReadTimeout;

    @Value("${server.ip}")
    private String ip;


    public String getUnifiedOrderUrl() {
        return unifiedOrderUrl;
    }

    public void setUnifiedOrderUrl(String unifiedOrderUrl) {
        this.unifiedOrderUrl = unifiedOrderUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public int getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public void setHttpConnectTimeout(int httpConnectTimeout) {
        this.httpConnectTimeout = httpConnectTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public void setHttpReadTimeout(int httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getQueryOrderUrl() {
        return queryOrderUrl;
    }

    public void setQueryOrderUrl(String queryOrderUrl) {
        this.queryOrderUrl = queryOrderUrl;
    }

    public String getCloseOrderUrl() {
        return closeOrderUrl;
    }

    public void setCloseOrderUrl(String closeOrderUrl) {
        this.closeOrderUrl = closeOrderUrl;
    }

    public String getRefundUrl() {
        return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }

    public String getRefundQueryUrl() {
        return refundQueryUrl;
    }

    public void setRefundQueryUrl(String refundQueryUrl) {
        this.refundQueryUrl = refundQueryUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGoodsBody() {
        return goodsBody;
    }

    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody;
    }

    public String getMicroPay() {
        return microPay;
    }

    public void setMicroPay(String microPay) {
        this.microPay = microPay;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getDownloadBill() {
        return downloadBill;
    }

    public void setDownloadBill(String downloadBill) {
        this.downloadBill = downloadBill;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getAuthCodeToOpenid() {
        return authCodeToOpenid;
    }

    public void setAuthCodeToOpenid(String authCodeToOpenid) {
        this.authCodeToOpenid = authCodeToOpenid;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
