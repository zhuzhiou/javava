package com.javava.pay.service;


import com.javava.pay.entity.Prepay;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface PrepayService {

    Prepay findByOutTradeNo(String outTradeNo);
}
