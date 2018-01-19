package cn.javava.thirdparty.weixin.service.impl;

import cn.javava.thirdparty.weixin.config.ApiConfig;
import cn.javava.thirdparty.weixin.entity.Prepay;
import cn.javava.thirdparty.weixin.repository.PrepayRepository;
import cn.javava.thirdparty.weixin.service.PrepayService;
import cn.javava.thirdparty.weixin.util.WxPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ApiConfig apiConfig;

    @Override
    public void save(Prepay prepay) {
        prepay.setAppId(apiConfig.getAppId());
        prepay.setBody(apiConfig.getGoodsBody());
        prepay.setCreateTime(new Date());
        prepay.setId(WxPayUtil.generateUUID());
        prepay.setMchId(apiConfig.getMchId());
        prepay.setNotifyUrl(apiConfig.getNotifyUrl());
        prepayRepository.save(prepay);
    }
}
