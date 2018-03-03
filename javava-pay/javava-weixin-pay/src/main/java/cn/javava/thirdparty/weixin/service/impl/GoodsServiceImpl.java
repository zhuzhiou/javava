package cn.javava.thirdparty.weixin.service.impl;

import cn.javava.thirdparty.weixin.entity.Goods;
import cn.javava.thirdparty.weixin.repository.GoodsRepository;
import cn.javava.thirdparty.weixin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wlrllr on 2018/1/15.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods findById(String id){
        return goodsRepository.findOne(id);
    }
}
