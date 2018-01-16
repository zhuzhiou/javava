package com.javava.pay.constant;

/**
 * 常量
 */
public class WxPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String SPLIT_SYMBOL = "_";
    /**
     * 充值
     */
    public static final String OPERATE_TYPE_RECHARGE = "recharge";
    /**
     * 消费
     */
    public static final String OPERATE_TYPE_PURCHASE = "purchase";

    /**
     * 钱包状态 默认有效
     */
    public static final String WALLET_STATUS_1="1";
    /**
     * 钱包状态 失效
     */
    public static final String WALLET_STATUS_0="0";


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

}

