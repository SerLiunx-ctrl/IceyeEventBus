package com.serliunx.iceye.core.builder;

import com.serliunx.iceye.core.bus.EventBus;
import com.serliunx.iceye.core.bus.IntegratedEventBus;

/**
 * @author SerLiunx
 * @since 1.0
 */
public final class EventBusBuilder {

    private EventBusBuilder(){}

    /**
     * 快速获取一个事件总线
     * <li> 集成的事件总线
     * @return 推荐的事件总线
     */
    public static EventBus getDefaultEventBus(){
        return new IntegratedEventBus();
    }


}
