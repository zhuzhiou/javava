package com.javava.pay.controller;

import com.javava.pay.config.ApiConfig;
import com.javava.pay.repository.GoodsRepository;
import com.javava.pay.service.GoodsService;
import com.javava.pay.service.PrepayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wlrllr on 2018/1/11.
 */
@RestController
public class WxManageController {

    @Autowired
    private ApiConfig apiConfig;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private PrepayService prepayService;
    @Autowired
    private GoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(WxManageController.class);




}
