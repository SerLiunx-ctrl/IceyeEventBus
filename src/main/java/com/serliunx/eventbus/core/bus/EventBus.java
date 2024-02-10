package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.core.Listener;

/**
 * 事件总线
 * @author SerLiunx
 * @since 1.0
 */
public interface EventBus {

    /**
     * 注册监听器
     * @param listener 监听器
     */
    void registerListener(Listener listener);

    /**
     * 发布事件
     * @param event 事件
     */
    void publish(Object event);
}
