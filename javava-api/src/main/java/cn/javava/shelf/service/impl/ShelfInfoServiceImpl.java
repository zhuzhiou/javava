package cn.javava.shelf.service.impl;

import cn.javava.shelf.constant.ShelfConstants;
import cn.javava.shelf.entity.ShelfBox;
import cn.javava.shelf.entity.ShelfInfo;
import cn.javava.shelf.repository.ShelfInfoRepository;
import cn.javava.shelf.service.ShelfBoxService;
import cn.javava.shelf.service.ShelfInfoService;
import cn.javava.shelf.service.ShelfRecordService;
import cn.javava.shelf.util.ShelfUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wlrllr on 2018/3/5.
 */
@Service
public class ShelfInfoServiceImpl implements ShelfInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ShelfInfoServiceImpl.class);

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
            String operateResult = ShelfConstants.COMMON_SUCCESS;
            try {
                info.setId(ShelfUtils.generateId());
                info.setGoodsNum(0);
                //未上线
                info.setIsAvailable("");
                shelfInfoRepository.save(info);
            } catch (Exception e) {
                operateResult = ShelfConstants.COMMON_FAILURE;
                logger.error("创建货架异常", e);
            }
            shelfRecordService.saveRecord(info.getId(), null, ShelfConstants.OPERATE_SHELF_CREATE, operateResult, "创建货架");
            return info;
        }
        return null;
    }

    @Override
    public ShelfInfo find(Long shelfId) {
        if (shelfId != 0)
            return shelfInfoRepository.findOne(shelfId);
        return null;
    }

    @Override
    public ShelfInfo update(ShelfInfo info) {
        return shelfInfoRepository.save(info);
    }

    @Override
    public boolean update(Long shelfId, Long boxId, String operate) {
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
        String operateResult = ShelfConstants.COMMON_SUCCESS;
        try {
            //上线/下线
            if (StringUtils.isNotBlank(online)) {
                info.setIsAvailable(online);
                if (ShelfConstants.COMMON_Y.equals(online)) {
                    int boxNum = shelfBoxService.online(info.getBoxNum(), shelfId);
                    info.setGoodsNum(info.getGoodsNum() + boxNum);
                } else {
                    //下线
                    int boxNum = shelfBoxService.offline(shelfId);
                    info.setGoodsNum(info.getGoodsNum() - boxNum);

                }
                shelfInfoRepository.save(info);
            }
            //补货/出货
            if (num != 0) {
                info.setGoodsNum(info.getGoodsNum() + num);
                shelfInfoRepository.save(info);
                if (num > 0) {
                    shelfBoxService.restock(boxId);
                } else {
                    shelfBoxService.deliver(boxId);
                }
            }
        } catch (Exception e) {
            operateResult = ShelfConstants.COMMON_FAILURE;
            logger.error(logInfo + "异常", e);
        }
        shelfRecordService.saveRecord(shelfId, boxId, operate, operateResult, logInfo);
        return true;
    }

    @Override
    public boolean updateBoxStatus(Long shelfId, Long boxId, String status) {
        String operateResult = ShelfConstants.COMMON_SUCCESS;
        String remark = "";
        try {
            ShelfInfo info = find(shelfId);
            ShelfBox box = shelfBoxService.updateStatus(boxId, status);
            if (ShelfConstants.BOX_STATUS_DAMAGED.equals(status)) {
                remark = "标记格口损坏";
                if (ShelfConstants.COMMON_Y.equals(box.getIsAvailable())) {
                    if( info.getGoodsNum() > 0){
                        info.setGoodsNum(info.getGoodsNum() - 1);
                        shelfInfoRepository.save(info);
                    }
                }
            }
        } catch (Exception e) {
            operateResult = ShelfConstants.COMMON_FAILURE;
            logger.error("更新格口状态异常", e);
        }
        shelfRecordService.saveRecord(shelfId, boxId, ShelfConstants.OPERATE_BOX_DAMAGED, operateResult, remark);
        return true;
    }
}
