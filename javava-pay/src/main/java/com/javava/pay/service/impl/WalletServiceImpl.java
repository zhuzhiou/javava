package com.javava.pay.service.impl;


import com.javava.pay.entity.Wallet;
import com.javava.pay.repository.WalletRepository;
import com.javava.pay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wlrllr on 2018/1/12.
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet findByOpenid(String openId) {
        return walletRepository.findByOpenId(openId);
    }

}
