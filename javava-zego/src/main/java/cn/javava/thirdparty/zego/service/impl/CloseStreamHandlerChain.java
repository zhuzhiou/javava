package cn.javava.thirdparty.zego.service.impl;

import cn.javava.thirdparty.zego.service.CloseStreamHandler;
import cn.javava.thirdparty.zego.vo.CloseStreamVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("closeStreamHandlerChain")
public class CloseStreamHandlerChain implements InitializingBean, CloseStreamHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void handle(CloseStreamVo vo) {
        if (closeStreamHandlers == null) {
            return;
        }
        for (CloseStreamHandler closeStreamHandler : closeStreamHandlers) {
            closeStreamHandler.handle(vo);
        }
    }

    private Collection<CloseStreamHandler> closeStreamHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, CloseStreamHandler> map = applicationContext.getBeansOfType(CloseStreamHandler.class);
        if (map != null) {
            if (closeStreamHandlers == null) {
                closeStreamHandlers = new ArrayList<>();
            } else {
                closeStreamHandlers.clear();
            }
            for (CloseStreamHandler handler : map.values()) {
                if (handler instanceof CloseStreamHandlerChain) {
                    continue;
                }
                closeStreamHandlers.add(handler);
            }
        }
    }
}
