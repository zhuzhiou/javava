package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.LiveRoomDao;
import cn.javava.thirdparty.zego.service.CloseLiveHandler;
import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("closeLiveHandler_room")
public class CloseLiveHandler_room implements CloseLiveHandler {

    @Autowired
    private LiveRoomDao liveRoomDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseLiveVo vo) {
        if (liveRoomDao.count(vo) > 0) {
            liveRoomDao.update(vo);
        } else {
            liveRoomDao.create(vo);
        }
    }
}
