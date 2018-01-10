package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.thirdparty.zego.service.OpenLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenLiveVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("openLiveHandlerChain")
public class OpenLiveHandlerChain implements InitializingBean, OpenLiveHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handle(OpenLiveVo vo) {
        if (openLiveHandlers == null) {
            return;
        }
        if (vo.getCreateTime() == null) {
            vo.setCreateTime(System.currentTimeMillis() / 1000);
        }
        for (OpenLiveHandler openLiveHandler : openLiveHandlers) {
            openLiveHandler.handle(vo);
        }
    }

    private Collection<OpenLiveHandler> openLiveHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, OpenLiveHandler> map = applicationContext.getBeansOfType(OpenLiveHandler.class);
        if (map != null) {
            if (openLiveHandlers == null) {
                openLiveHandlers = new ArrayList<>();
            } else {
                openLiveHandlers.clear();
            }
            for (OpenLiveHandler handler : map.values()) {
                if (handler instanceof OpenLiveHandlerChain) {
                    continue;
                }
                openLiveHandlers.add(handler);
            }
        }
    }
}
