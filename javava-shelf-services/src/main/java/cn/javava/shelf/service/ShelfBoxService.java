package cn.javava.shelf.service;


import cn.javava.shelf.entity.ShelfInfo;

/**
 * Created by wlrllr on 2018/3/5.
 */
public interface ShelfBoxService {

    void online(int boxNum,String shelfId);

    void offline(String shelfId);

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

    void updateStatus(String boxId,String isAvailable);
}
