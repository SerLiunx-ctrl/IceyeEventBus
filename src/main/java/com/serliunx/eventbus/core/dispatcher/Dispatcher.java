package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.MethodHolder;
import com.serliunx.eventbus.core.EventRegistry;

import java.util.List;
import java.util.Map;

/**
 * 调度器
 * @author SerLiunx
 * @since 1.0
 */
@FunctionalInterface
@SuppressWarnings("all")
public interface Dispatcher {

    /**
     * 调度监听器
     * @param eventRegistry 订阅者信息
     * @param event 事件实体
     */
    void dispatch(EventRegistry eventRegistry, Object event);

    /**
     * 校验数据
     * @param eventRegistry 订阅者信息
     * @param event 事件实体
     * @return 有数据调度返回真, 否则返回假
     */
    default boolean validateContext(EventRegistry eventRegistry, Object event){
        Map<Class<?>, List<MethodHolder>> subscribers = eventRegistry.getSubscribers();
        if(subscribers == null){
            return false;
        }
        List<MethodHolder> syncListeners = subscribers.get(event.getClass());
        return syncListeners != null;
    }
}
