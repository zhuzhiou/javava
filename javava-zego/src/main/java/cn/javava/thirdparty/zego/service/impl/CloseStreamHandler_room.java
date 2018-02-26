package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.RoomDao;
import cn.javava.thirdparty.zego.service.CloseStreamHandler;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("closeStreamHandler_room")
public class CloseStreamHandler_room implements CloseStreamHandler {

    @Autowired
    private RoomDao roomDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        if (roomDao.count(vo) > 0) {
            roomDao.update(vo);
        } else {
            roomDao.insert(vo);
        }
    }
}
