package cn.javava.weixin.pay.service.impl;


import cn.javava.weixin.pay.config.ApiPayConfig;
import cn.javava.weixin.pay.entity.Prepay;
import cn.javava.weixin.pay.repository.PrepayRepository;
import cn.javava.weixin.pay.service.PrepayService;
import cn.javava.weixin.pay.util.WxPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wlrllr on 2018/1/12.
 */
@Service
public class PrepayServiceImpl implements PrepayService {

    @Autowired
    private PrepayRepository prepayRepository;
    @Autowired
    private ApiPayConfig apiPayConfig;

    @Override
    public void save(Prepay prepay) {
        prepay.setAppId(apiPayConfig.getAppId());
        prepay.setBody(apiPayConfig.getGoodsBody());
        prepay.setCreateTime(new Date());
        prepay.setId(WxPayUtil.generateUUID());
        prepay.setMchId(apiPayConfig.getMchId());
        prepay.setNotifyUrl(apiPayConfig.getNotifyUrl());
        prepayRepository.save(prepay);
    }

    @Override
    public Prepay findByOutTradeNo(String outTradeNo) {
        Prepay prepay = new Prepay();
        prepay.setOutTradeNo(outTradeNo);
        return prepayRepository.findOne(Example.of(prepay));
    }
}
