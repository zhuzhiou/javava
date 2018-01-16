package com.javava.thirdparty.weixin.service;

import com.javava.thirdparty.weixin.entity.Wallet;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface WalletService {

    void refreshWallet(Map<String,String> params, String userId);

    Wallet findByOpenId(String openId);

    Wallet save(Wallet wallet);
}
