package com.javava.pay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wlrllr on 2018/1/12.
 * 消费记录，包含充值和消费
 */
@Table(name = "jww_flow_record")
@Entity
@lombok.Data
public class FlowRecord {
    @Id
    @Column(name="ID")
    private String id;
    /**
     * 消费金额
     */
    @Column(name="MONEY")
    private Double money;
    /**
     * 消费类型 消费/充值
     */
    @Column(name="TYPE")
    private String type;
    /**
     * 操作人
     */
    @Column(name="OPERATOR")
    private String operator;
    /**
     * 操作时间
     */
    @Column(name="OPERATE_TIME")
    private Date operateTime;
    /**
     * 用户ID
     */
    @Column(name="USER_ID")
    private String userId;
    /**
     * 绑定的钱包ID
     */
    @Column(name="WALLET_ID")
    private String walletId;

    /**
     * 业务结果
     */
    @Column(name="RESULT_CODE")
    private String resultCode;
    /**
     *  用户标识
     */
    @Column(name="OPENID")
    private String openid;
    /**
     *付款银行
     */
    @Column(name="BANK_TYPE")
    private String bankType;
    /**
     * 微信支付订单号
     */
    @Column(name="TRANSACTION_ID")
    private String transactionId;
    /**
     * 商户订单号
     */
    @Column(name="OUT_TRADE_NO")
    private String outTradeNo;
    /**
     * 设备号
     */
    @Column(name="DEVICE_INFO")
    private String deviceInfo;
    /**
     * 返回结果
     */
    @Column(name="REMARK")
    private String remark;
}
