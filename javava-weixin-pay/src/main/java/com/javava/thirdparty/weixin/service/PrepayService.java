package com.javava.thirdparty.weixin.service;

import com.javava.thirdparty.weixin.entity.Prepay;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface PrepayService {
    void save(Prepay prepay);

    Prepay findByOutTradeNo(String outTradeNo);
}
