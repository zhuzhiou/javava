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
     * @param result
     * @param remark
     */
    void saveRecord(Long shelfId,Long boxId,String operate,String result,String remark);
}
