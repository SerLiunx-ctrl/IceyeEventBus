package com.serliunx.iceye.boot.config;

import com.serliunx.iceye.core.bus.EventBus;
import com.serliunx.iceye.core.bus.SyncEventBus;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SerLiunx
 * @since 1.0
 */
@ConfigurationProperties("iceye-eventbus")
public class IceyeEventbusConfiguration {

    /**
     * 默认的事件总线实现
     */
    private Class<? extends EventBus> defaultEventbus = SyncEventBus.class;

    public Class<? extends EventBus> getDefaultEventbus() {
        return defaultEventbus;
    }

    public void setDefaultEventbus(Class<? extends EventBus> defaultEventbus) {
        this.defaultEventbus = defaultEventbus;
    }
}
