package cn.javava.weixin.pay.service;

import cn.javava.weixin.pay.entity.Prepay;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface PrepayService {
    void save(Prepay prepay);

    Prepay findByOutTradeNo(String outTradeNo);
}
