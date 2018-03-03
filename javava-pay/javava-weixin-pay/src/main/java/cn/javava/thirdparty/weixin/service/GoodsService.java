package cn.javava.thirdparty.weixin.service;

import cn.javava.thirdparty.weixin.entity.Goods;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface GoodsService {
    Goods findById(String id);
}
