package cn.javava.weixin.pay.service;

import cn.javava.weixin.pay.entity.Goods;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface GoodsService {
    Goods findById(String id);
}
