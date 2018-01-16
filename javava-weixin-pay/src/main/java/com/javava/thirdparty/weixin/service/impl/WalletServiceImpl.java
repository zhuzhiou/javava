package com.javava.thirdparty.weixin.service.impl;

import com.javava.thirdparty.weixin.service.WalletService;
import com.javava.thirdparty.weixin.util.WxPayUtil;
import com.javava.thirdparty.weixin.entity.Wallet;
import com.javava.thirdparty.weixin.repository.WalletRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wlrllr on 2018/1/12.
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void refreshWallet(Map<String, String> params, String userId) {
        if (StringUtils.isNotBlank(params.get("openid"))) {
            Wallet wallet = findByOpenId(params.get("openid"));
            Integer fee = Integer.parseInt(params.get("total_fee"));
            if (wallet == null) {
                wallet = new Wallet();
                wallet.setId(WxPayUtil.generateUUID());
                wallet.setBalance(fee);
                wallet.setOpenid(params.get("openid"));
                wallet.setUserId(userId);
            } else {
                wallet.setBalance(wallet.getBalance() + fee);
            }
            save(wallet);
        }
    }

    @Override
    public Wallet findByOpenId(String openId) {
        return walletRepository.findByOpenid(openId);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.saveAndFlush(wallet);
    }
}