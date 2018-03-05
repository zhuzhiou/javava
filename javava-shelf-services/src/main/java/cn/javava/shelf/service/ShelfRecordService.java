package cn.javava.shelf.service;



/**
 * Created by wlrllr on 2018/3/5.
 */
public interface ShelfRecordService {

    /**
     * 保存操作记录
     * @param shelfId
     * @param boxId
     * @param operate
     * @param remark
     */
    void saveRecord(String shelfId,String boxId,String operate,String remark);
}
