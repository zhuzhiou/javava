package cn.javava.pay.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wlrllr on 2018/1/12.
 * 用户钱包
 */
@Table(name = "jww_pay_wallet")
@Entity
@lombok.Data
public class Wallet {
    @Id
    @Column(name = "ID")
    private String id;
    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;
    /**
     * 用户标识
     */
    @Column(name = "OPENID")
    private String openid;
    /**
     * 钱包余额
     */
    @Column(name = "BALANCE")
    private Integer balance;
    /**
     * 钱包创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 钱包状态
     */
    @Column(name = "STATUS")
    private String status;
}
