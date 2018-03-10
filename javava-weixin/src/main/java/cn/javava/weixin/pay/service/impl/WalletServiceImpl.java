package cn.javava.weixin.pay.service.impl;

import cn.javava.weixin.pay.constant.WxPayConstants;
import cn.javava.weixin.pay.entity.Wallet;
import cn.javava.weixin.pay.repository.WalletRepository;
import cn.javava.weixin.pay.service.WalletService;
import cn.javava.weixin.pay.util.WxPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
                wallet.setCreateTime(new Date());
                wallet.setStatus(WxPayConstants.WALLET_STATUS_1);
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
