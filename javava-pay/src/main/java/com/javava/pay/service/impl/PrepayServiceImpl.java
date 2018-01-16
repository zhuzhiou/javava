package com.javava.pay.service.impl;



import com.javava.pay.config.ApiConfig;
import com.javava.pay.entity.Prepay;
import com.javava.pay.repository.PrepayRepository;
import com.javava.pay.service.PrepayService;
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

    @Override
    public Prepay findByOutTradeNo(String outTradeNo) {
        Prepay prepay = new Prepay();
        prepay.setOutTradeNo(outTradeNo);
        return prepayRepository.findOne(Example.of(prepay));
    }
}
