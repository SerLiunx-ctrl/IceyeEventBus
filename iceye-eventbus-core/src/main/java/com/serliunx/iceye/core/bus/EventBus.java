package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.core.Listener;

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
     * 批量注册监听器
     * @param listeners 监听器
     */
    void registerListener(Listener...listeners);

    /**
     * 发布事件
     * @param event 事件
     */
    void publish(Object event);
}
