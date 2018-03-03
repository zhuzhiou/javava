package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.dao.StreamDao;
import cn.javava.thirdparty.zego.service.OpenStreamHandler;
import cn.javava.thirdparty.zego.vo.OpenStreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("openStreamHandler_stream")
public class OpenStreamHandler_stream implements OpenStreamHandler {

    @Autowired
    private StreamDao streamDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        streamDao.insert(vo);
    }
}
