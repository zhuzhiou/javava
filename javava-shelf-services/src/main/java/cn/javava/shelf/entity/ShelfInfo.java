package cn.javava.shelf.entity;

import cn.javava.shelf.constant.ShelfConstants;

import javax.persistence.*;
import java.util.List;

/**
 * create by wlrllr 2018/3/5
 * 记录货架信息
 */
@Table(name = "jww_shelf_info")
@Entity
@lombok.Data
public class ShelfInfo {

    @Id
    @Column(name="ID")
    private String id;
    /**
     * 货架名字
     */
    @Column(name="NAME")
    private String name;
    /**
     * 格口数量
     */
    @Column(name="BOX_NUM")
    private Integer boxNum;
    /**
     * 货物数量
     */
    @Column(name="GOODS_NUM")
    private Integer goodsNum;
    /**
     * 是否可用Y/N
     */
    @Column(name="IS_AVAILABLE")
    private String isAvailable;
    /**
     * 描述
     */
    @Column(name="REMARK")
    private String remark;
}
