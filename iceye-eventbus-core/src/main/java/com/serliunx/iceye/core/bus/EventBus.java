package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.util.CandidateMethodScanner;

import java.util.Collection;

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
    void registerListeners(Listener...listeners);

    /**
     * 批量注册监听器
     * @param listeners 监听器
     */
    void registerListeners(Collection<? extends Listener> listeners);

    /**
     * 发布事件
     * @param event 事件
     */
    void publish(Object event);

    /**
     * 设置方法扫描器
     * @param scanner 方法扫描器
     */
    void setCandidateMethodScanner(CandidateMethodScanner scanner);
}
