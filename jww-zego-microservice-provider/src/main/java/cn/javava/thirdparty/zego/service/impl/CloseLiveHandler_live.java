package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.LiveDao;
import cn.javava.thirdparty.zego.service.CloseLiveHandler;
import cn.javava.thirdparty.zego.vo.CloseLiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("closeLiveHandler_live")
public class CloseLiveHandler_live implements CloseLiveHandler {

    @Autowired
    private LiveDao liveDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseLiveVo vo) {
        liveDao.update(vo);
    }
}
