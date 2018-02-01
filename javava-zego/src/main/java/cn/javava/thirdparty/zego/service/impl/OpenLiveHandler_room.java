package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.LiveRoomDao;
import cn.javava.thirdparty.zego.service.OpenLiveHandler;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("openLiveHandler_room")
public class OpenLiveHandler_room implements OpenLiveHandler {

    @Autowired
    private LiveRoomDao liveRoomDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenLiveVo vo) {
        if (liveRoomDao.count(vo) > 0) {
            liveRoomDao.update(vo);
        } else {
            liveRoomDao.create(vo);
        }
    }
}
