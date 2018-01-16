package com.javava.pay.service;


import com.javava.pay.entity.Wallet;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface WalletService {

    Wallet findByOpenid(String openId);

}
