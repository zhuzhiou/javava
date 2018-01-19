package cn.javava.api.pay.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wlrllr on 2018/1/12.
 * 预付表，只记录生成的预付订单，保存部分业务数据，包含userId等
 */
@Table(name = "jww_pay_prepay")
@Entity
@lombok.Data
public class Prepay {

    @Id
    @Column(name="ID")
    private String id;
    @Column(name="PREPAY_ID")
    private String prepayId;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="DEVICE_INFO")
    private String deviceInfo;
    @Column(name="PRODUCT_ID")
    private String productId;
    @Column(name="TOTAL_FEE")
    private Integer totalFee;
    @Column(name="OUT_TRADE_NO")
    private String outTradeNo;
    @Column(name="NOTIFY_URL")
    private String notifyUrl;
    @Column(name="BODY")
    private String body;
    @Column(name="APP_ID")
    private String appId;
    @Column(name="MCH_ID")
    private String mchId;
    @Column(name="CREATE_TIME")
    private Date createTime;

}
