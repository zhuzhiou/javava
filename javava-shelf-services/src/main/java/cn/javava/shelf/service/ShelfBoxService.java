package cn.javava.shelf.service;


import cn.javava.shelf.entity.ShelfBox;
import cn.javava.shelf.entity.ShelfInfo;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
public interface ShelfBoxService {

    int online(int boxNum, String shelfId);

    int offline(String shelfId);

    /**
     * 出货
     * @param boxId
     */
    void deliver(String boxId);

    /**
     * 补货
     * @param boxId
     */
    void restock(String boxId);

    /**
     * 更新格口状态损坏/正常
     * @param boxId
     * @param status
     */
    void updateStatus(String boxId,String status);
}
