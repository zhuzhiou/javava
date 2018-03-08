package cn.javava.shelf.service.impl;

import cn.javava.shelf.entity.ShelfRecord;
import cn.javava.shelf.repository.ShelfRecordRepository;
import cn.javava.shelf.service.ShelfRecordService;
import cn.javava.shelf.util.ShelfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Service
public class ShelfRecordServiceImpl implements ShelfRecordService {

    @Autowired
    private ShelfRecordRepository shelfRecordRepository;

    @Override
    public void saveRecord(Long shelfId,Long boxId, String operate,String result, String remark) {
        ShelfRecord record = new ShelfRecord();
        record.setId(ShelfUtils.generateId());
        record.setRemark(remark);
        record.setOperateType(operate);
        record.setShelfId(shelfId);
        record.setBoxId(boxId);
        record.setOperateResult(result);
        record.setOperateTime(new Date());
        shelfRecordRepository.save(record);
    }
}
