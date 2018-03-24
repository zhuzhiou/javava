package cn.javava.weixin.component.util;

import java.io.Serializable;

/**
 * <p>
 *     微信客户端上下文
 * </p>
 *
 * @since 2018-02-05
 * @author zhuzhiou
 */
public class ComponentClientContext implements Serializable {

    private ComponentToken componentToken;

    public ComponentClientContext() {
    }

    public ComponentClientContext(ComponentToken componentToken) {
        this.componentToken = componentToken;
    }

    public ComponentToken getComponentToken() {
        return componentToken;
    }

    public void setComponentToken(ComponentToken componentToken) {
        this.componentToken = componentToken;
    }
}
