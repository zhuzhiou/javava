package com.javava.pay.controller;

import com.javava.pay.config.ApiConfig;
import com.javava.pay.entity.FlowRecord;
import com.javava.pay.repository.GoodsRepository;
import com.javava.pay.service.FlowRecordService;
import com.javava.pay.service.GoodsService;
import com.javava.pay.service.PrepayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wlrllr on 2018/1/11.
 */
@RestController
@RequestMapping("wallet")
public class WxManageController {

    private static final Logger logger = LoggerFactory.getLogger(WxManageController.class);

    @Autowired
    private FlowRecordService flowRecordService;

    @RequestMapping("/flowRecord")
    public List<FlowRecord> listWalletFlowRecord(@ModelAttribute FlowRecord record){
        List<FlowRecord> list = flowRecordService.list(record,null,null);
        return list;
    }



}
