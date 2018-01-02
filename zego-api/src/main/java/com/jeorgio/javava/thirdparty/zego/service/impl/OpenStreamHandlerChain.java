package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.thirdparty.zego.OpenStreamVo;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("openStreamHandlerChain")
public class OpenStreamHandlerChain implements InitializingBean, OpenStreamHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(OpenStreamVo vo) {
        if (openStreamHandlers == null) {
            return;
        }
        if (vo.getCreateTime() == null) {
            vo.setCreateTime(System.currentTimeMillis() / 1000);
        }
        for (OpenStreamHandler openStreamHandler : openStreamHandlers) {
            openStreamHandler.handle(vo);
        }
    }

    private Collection<OpenStreamHandler> openStreamHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, OpenStreamHandler> map = applicationContext.getBeansOfType(OpenStreamHandler.class);
        if (map != null) {
            if (openStreamHandlers == null) {
                openStreamHandlers = new ArrayList<>();
            } else {
                openStreamHandlers.clear();
            }
            for (OpenStreamHandler handler : map.values()) {
                if (handler instanceof OpenStreamHandlerChain) {
                    continue;
                }
                openStreamHandlers.add(handler);
            }
        }
    }
}
