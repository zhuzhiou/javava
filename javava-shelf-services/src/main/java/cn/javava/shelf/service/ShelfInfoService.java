package cn.javava.shelf.service;


import cn.javava.shelf.entity.ShelfInfo;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
public interface ShelfInfoService {

    /**
     * 货架列表
     * @param info
     * @return
     */
    List<ShelfInfo> list(ShelfInfo info);

    /**
     * 创建货架，返回货架
     * @param info
     * @return
     */
    ShelfInfo createShelf(ShelfInfo info);

    ShelfInfo find(String shelfId);

    ShelfInfo update(ShelfInfo info);

    /**
     * 上线/下线,出货/补货
     * @param shelfId
     * @param boxId
     * @param operate
     * @return
     */
    boolean update(String shelfId,String boxId, String operate);

    boolean updateBoxStatus(String shelfId,String boxId, String status);
}
