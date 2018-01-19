package cn.javava.pay.service.impl;


import cn.javava.pay.entity.Wallet;
import cn.javava.pay.repository.WalletRepository;
import cn.javava.pay.service.WalletService;
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
        return walletRepository.findByOpenid(openId);
    }

}
