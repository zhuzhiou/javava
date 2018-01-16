package com.javava.thirdparty.weixin.entity;

import javax.persistence.*;

/**
 * Created by wlrllr on 2018/1/12.
 * 商品表，记录商品信息
 */
@Table(name = "jww_goods")
@Entity
@lombok.Data
public class Goods {

    @Id
    @Column(name="ID")
    private String id;
    /**
     * 标题
     */
    @Column(name="TITLE")
    private String title;
    /**
     * 商品名称
     */
    @Column(name="NAME")
    private String name;
    /**
     * 商品描述
     */
    @Column(name="DESCRIPTION")
    private String description;
    /**
     * 商品价格
     */
    @Column(name="PRICE")
    private Integer price;
}
