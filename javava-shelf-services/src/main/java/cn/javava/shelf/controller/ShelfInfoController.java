package cn.javava.shelf.controller;

import cn.javava.shelf.constant.ShelfConstants;
import cn.javava.shelf.entity.ShelfInfo;
import cn.javava.shelf.service.ShelfInfoService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wlrllr on 2018/1/4.
 */
@RestController
@RequestMapping("/shelfs/")
public class ShelfInfoController {

    private static final Logger logger = LoggerFactory.getLogger(ShelfInfoController.class);

    @Autowired
    private ShelfInfoService shelfInfoService;

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
    public ShelfInfo detail(@PathVariable String shelfId) {
        return shelfInfoService.find(shelfId);
    }

    @PatchMapping(value = "{shelfId}")
    public ShelfInfo update(@PathVariable String shelfId) {
        return shelfInfoService.find(shelfId);
    }

    @PostMapping(value = "{shelfId}/up")
    public JSONObject online(@PathVariable String shelfId) {
        JSONObject resp = new JSONObject();
        boolean flag = shelfInfoService.update(shelfId, null, ShelfConstants.OPERATE_SHELF_ONLINE);
        resp.put("status", flag);
        if (flag) {
            resp.put("message", "上线成功");
        } else {
            resp.put("message", "上线失败");
        }
        return resp;
    }

    /**
     * 下线
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/down")
    public JSONObject offline(@PathVariable String shelfId) {
        JSONObject resp = new JSONObject();
        boolean flag = shelfInfoService.update(shelfId, null, ShelfConstants.OPERATE_SHELF_OFFLINE);
        resp.put("status", flag);
        if (flag) {
            resp.put("message", "下线成功");
        } else {
            resp.put("message", "下线失败");
        }
        return resp;
    }

    /**
     * 出货
     *
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/deliver")
    public JSONObject deliver(@PathVariable String shelfId, String boxId) {
        JSONObject resp = new JSONObject();
        boolean flag = shelfInfoService.update(shelfId, boxId, ShelfConstants.OPERATE_BOX_DELIVER);
        resp.put("status", flag);
        if (flag) {
            resp.put("message", "出货成功");
        } else {
            resp.put("message", "出货失败");
        }
        return resp;
    }

    /**
     * 补货
     *
     * @param shelfId
     * @return
     */
    @PostMapping(value = "{shelfId}/restock")
    public JSONObject restock(@PathVariable String shelfId, String boxId) {
        JSONObject resp = new JSONObject();
        boolean flag = shelfInfoService.update(shelfId, boxId, ShelfConstants.OPERATE_BOX_RESTOCK);
        resp.put("status", flag);
        if (flag) {
            resp.put("message", "补货成功");
        } else {
            resp.put("message", "补货失败");
        }
        return resp;
    }
}
