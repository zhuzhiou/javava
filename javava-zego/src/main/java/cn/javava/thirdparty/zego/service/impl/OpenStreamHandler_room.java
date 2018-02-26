package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.RoomDao;
import cn.javava.thirdparty.zego.service.OpenStreamHandler;
import cn.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("openStreamHandler_room")
public class OpenStreamHandler_room implements OpenStreamHandler {

    @Autowired
    private RoomDao roomDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        if (roomDao.count(vo) > 0) {
            roomDao.update(vo);
        } else {
            roomDao.insert(vo);
        }
    }
}
