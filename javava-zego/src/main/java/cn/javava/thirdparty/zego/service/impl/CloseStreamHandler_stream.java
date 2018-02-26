package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.StreamDao;
import cn.javava.thirdparty.zego.dao.StreamHisDao;
import cn.javava.thirdparty.zego.service.CloseStreamHandler;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("closeStreamHandler_stream")
public class CloseStreamHandler_stream implements CloseStreamHandler {

    @Autowired
    private StreamHisDao streamHisDao;

    @Autowired
    private StreamDao streamDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        streamHisDao.insert(vo);
        streamDao.delete(vo);
    }
}
