package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.LiveDao;
import cn.javava.thirdparty.zego.service.OpenLiveHandler;
import cn.javava.thirdparty.zego.vo.OpenLiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("openLiveHandler_live")
public class OpenLiveHandler_live implements OpenLiveHandler {

    @Autowired
    private LiveDao liveDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenLiveVo vo) {
        liveDao.create(vo);
    }
}
