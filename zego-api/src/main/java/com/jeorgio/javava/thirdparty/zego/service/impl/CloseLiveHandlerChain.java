package com.jeorgio.javava.thirdparty.zego.service.impl;

import com.jeorgio.javava.thirdparty.zego.service.CloseLiveHandler;
import com.jeorgio.javava.thirdparty.zego.vo.CloseLiveVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("closeLiveHandlerChain")
public class CloseLiveHandlerChain implements InitializingBean, CloseLiveHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handle(CloseLiveVo vo) {
        if (closeLiveHandlers == null) {
            return;
        }
        for (CloseLiveHandler closeLiveHandler : closeLiveHandlers) {
            closeLiveHandler.handle(vo);
        }
    }

    private Collection<CloseLiveHandler> closeLiveHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, CloseLiveHandler> map = applicationContext.getBeansOfType(CloseLiveHandler.class);
        if (map != null) {
            if (closeLiveHandlers == null) {
                closeLiveHandlers = new ArrayList<>();
            } else {
                closeLiveHandlers.clear();
            }
            for (CloseLiveHandler handler : map.values()) {
                if (handler instanceof CloseLiveHandlerChain) {
                    continue;
                }
                closeLiveHandlers.add(handler);
            }
        }
    }
}
