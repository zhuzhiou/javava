package com.javava.thirdparty.weixin.service.impl;

import com.javava.thirdparty.weixin.entity.Goods;
import com.javava.thirdparty.weixin.repository.GoodsRepository;
import com.javava.thirdparty.weixin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wlrllr on 2018/1/15.
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods findById(String id){
        return goodsRepository.findOne(id);
    }
}
