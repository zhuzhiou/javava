package cn.javava.shelf.service.impl;

import cn.javava.shelf.entity.ShelfRecord;
import cn.javava.shelf.repository.ShelfRecordRepository;
import cn.javava.shelf.service.ShelfRecordService;
import com.fasterxml.uuid.Generators;
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
    public void saveRecord(String shelfId,String binId, String operate, String remark) {
        ShelfRecord record = new ShelfRecord();
        record.setId(Generators.timeBasedGenerator().generate().toString());
        record.setRemark(remark);
        record.setOperateType(operate);
        record.setShelfId(shelfId);
        record.setBinId(binId);
        record.setOperateTime(new Date());
        shelfRecordRepository.save(record);
    }
}
