package cn.javava.pay.service;


import cn.javava.pay.entity.Wallet;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface WalletService {

    Wallet findByOpenid(String openId);

}
