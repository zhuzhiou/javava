package cn.javava.pay.service;


import cn.javava.pay.entity.Prepay;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface PrepayService {

    Prepay findByOutTradeNo(String outTradeNo);
}
