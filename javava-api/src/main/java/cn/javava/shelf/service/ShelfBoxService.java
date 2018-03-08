package cn.javava.shelf.service;


import cn.javava.shelf.entity.ShelfBox;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
public interface ShelfBoxService {

    int online(int boxNum, Long shelfId);

    int offline(Long shelfId);

    /**
     * 出货
     * @param boxId
     */
    void deliver(Long boxId);

    /**
     * 补货
     * @param boxId
     */
    void restock(Long boxId);

    /**
     * 更新格口状态损坏/正常
     * @param boxId
     * @param status
     */
    ShelfBox updateStatus(Long boxId,String status);

    List<ShelfBox> findByShelfId(Long shelfId);
}
