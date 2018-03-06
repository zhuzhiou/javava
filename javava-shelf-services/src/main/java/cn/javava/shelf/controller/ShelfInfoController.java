package cn.javava.shelf.controller;

import cn.javava.shelf.constant.ShelfConstants;
import cn.javava.shelf.entity.ShelfInfo;
import cn.javava.shelf.service.ShelfBoxService;
import cn.javava.shelf.service.ShelfInfoService;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.utils.StringComparable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wlrllr on 2018/1/4.
 */
@RestController
@RequestMapping("/shelves/")
public class ShelfInfoController {

    private static final Logger logger = LoggerFactory.getLogger(ShelfInfoController.class);

    @Autowired
    private ShelfInfoService shelfInfoService;
    @Autowired
    private ShelfBoxService shelfBoxService;

    @GetMapping(value = "")
    public List<ShelfInfo> list() {
        return shelfInfoService.list(new ShelfInfo());
    }

    @PostMapping(value = "")
    public ShelfInfo create(int column, @RequestParam(value = "remark", required = false) String remark, String name) {
        ShelfInfo info = new ShelfInfo();
        info.setBoxNum(column);
        info.setRemark(remark);
        info.setName(name);
        return shelfInfoService.createShelf(info);
    }

    @GetMapping(value = "{shelfId}")
    public ShelfInfo detail(@PathVariable Long shelfId) {
        ShelfInfo shelfInfo = shelfInfoService.find(shelfId);
        if (shelfInfo != null)
            shelfInfo.setBoxes(shelfBoxService.findByShelfId(shelfId));
        return shelfInfo;
    }

    @PatchMapping(value = "{shelfId}")
    public ShelfInfo update(@PathVariable Long shelfId, String shelfName, Integer boxNum, Integer goodsNum, String remark) {
        ShelfInfo info = shelfInfoService.find(shelfId);
        if(StringUtils.isNotBlank(info.getIsAvailable())){
            logger.error("货架已经上线/下线，无法修改,货架信息【{}】",JSONObject.toJSONString(info));
            return null;
        }
        if (StringUtils.isNotBlank(shelfName)) {
            info.setName(shelfName);
        }
        if (boxNum != null) {
            info.setBoxNum(boxNum);
        }
        if (goodsNum != null) {
            info.setGoodsNum(goodsNum);
        }
        if (StringUtils.isNotBlank(remark)) {
            info.setRemark(remark);
        }
        return shelfInfoService.update(info);
    }

    /**
     * 上线，下线
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/maintain")
    public boolean maintain(@PathVariable Long shelfId,@RequestParam String operateType) {
        if(ShelfConstants.OPERATE_SHELF_ONLINE.equals(operateType)
                || ShelfConstants.OPERATE_SHELF_OFFLINE.equals(operateType)){
            return shelfInfoService.update(shelfId, null, operateType);
        }
        return false;
    }

    /**
     * 出货
     *
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/deliver")
    public boolean deliver(@PathVariable Long shelfId, Long boxId) {
        return shelfInfoService.update(shelfId, boxId, ShelfConstants.OPERATE_BOX_DELIVER);
    }

    /**
     * 补货
     *
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/restock")
    public boolean restock(@PathVariable Long shelfId, Long boxId) {
        return shelfInfoService.update(shelfId, boxId, ShelfConstants.OPERATE_BOX_RESTOCK);

    }

    /**
     * 格口损坏
     *
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/box/damaged")
    public boolean boxStatus(@PathVariable Long shelfId, @RequestParam Long boxId) {
        return shelfInfoService.updateBoxStatus(shelfId, boxId, ShelfConstants.BOX_STATUS_DAMAGED);
    }
}
