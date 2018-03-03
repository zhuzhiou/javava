package cn.javava.pay.weixin.repository;

import cn.javava.pay.weixin.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wlrllr on 2018/1/12.
 */
public interface WalletRepository extends JpaRepository<Wallet,String> {

    Wallet findByOpenid(String openId);
}
