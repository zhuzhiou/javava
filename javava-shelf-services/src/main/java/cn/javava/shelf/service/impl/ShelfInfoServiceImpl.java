package cn.javava.shelf.service.impl;

import cn.javava.shelf.constant.ShelfConstants;
import cn.javava.shelf.entity.ShelfBox;
import cn.javava.shelf.entity.ShelfInfo;
import cn.javava.shelf.repository.ShelfBoxRepository;
import cn.javava.shelf.repository.ShelfInfoRepository;
import cn.javava.shelf.service.ShelfBoxService;
import cn.javava.shelf.service.ShelfInfoService;
import cn.javava.shelf.service.ShelfRecordService;
import com.fasterxml.uuid.Generators;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Service
public class ShelfInfoServiceImpl implements ShelfInfoService {

    @Autowired
    private ShelfInfoRepository shelfInfoRepository;
    @Autowired
    private ShelfBoxService shelfBoxService;
    @Autowired
    private ShelfRecordService shelfRecordService;

    @Override
    public List<ShelfInfo> list(ShelfInfo info) {
        return shelfInfoRepository.findAll(Example.of(info));
    }

    @Override
    public ShelfInfo createShelf(ShelfInfo info) {
        if (info != null && info.getBoxNum() != null) {
            info.setId(Generators.timeBasedGenerator().generate().toString());
            info.setGoodsNum(0);
            //未上线
            info.setIsAvailable("");
            shelfInfoRepository.save(info);
            shelfRecordService.saveRecord(info.getId(), null, ShelfConstants.OPERATE_SHELF_CREATE, "创建货架");
            return info;
        }
        return null;
    }

    @Override
    public ShelfInfo find(String shelfId) {
        if (StringUtils.isNotBlank(shelfId))
            return shelfInfoRepository.findOne(shelfId);
        return null;
    }

    @Override
    public boolean update(ShelfInfo info) {
        shelfInfoRepository.save(info);
        return true;
    }

    @Override
    public boolean update(String shelfId, String boxId, String operate) {
        ShelfInfo info = find(shelfId);
        String online = "";
        int num = 0;
        String logInfo = "";
        switch (operate) {
            case ShelfConstants.OPERATE_SHELF_ONLINE:
                online = ShelfConstants.COMMON_Y;
                logInfo = "货架上线";
                break;
            case ShelfConstants.OPERATE_SHELF_OFFLINE:
                logInfo = "货架下线";
                online = ShelfConstants.COMMON_N;
                break;
            case ShelfConstants.OPERATE_BOX_RESTOCK:
                logInfo = "补货";
                num = 1;
                break;
            case ShelfConstants.OPERATE_BOX_DELIVER:
                logInfo = "出货";
                num = -1;
                break;
        }
        shelfRecordService.saveRecord(shelfId, boxId, operate, logInfo);
        //上线/下线
        if (StringUtils.isNotBlank(online)) {
            info.setIsAvailable(online);
            shelfInfoRepository.save(info);
            if(ShelfConstants.COMMON_Y.equals(online)){
                shelfBoxService.online(info.getBoxNum(),shelfId);
            }else{
                //下线
                shelfBoxService.offline(shelfId);
            }
        }
        //补货/出货
        if (num != 0) {
            info.setGoodsNum(info.getGoodsNum() + num);
            shelfInfoRepository.save(info);
            if(num>0){
                shelfBoxService.restock(boxId);
            }else{
                shelfBoxService.deliver(boxId);
            }
        }
        return true;
    }
}
