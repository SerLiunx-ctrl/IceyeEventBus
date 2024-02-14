package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.MethodHolder;
import com.serliunx.eventbus.core.event.Event;

import java.util.List;
import java.util.Map;

/**
 * 调度器
 * @author SerLiunx
 * @since 1.0
 */
@SuppressWarnings("all")
public interface Dispatcher {

    /**
     * 调度监听器
     * @param eventRegistry 订阅者信息
     * @param event 事件实体
     */
    void dispatch(EventRegistry eventRegistry, Object event);

    /**
     * 调度指定监听器
     * <li> 用于调度实现了{@link Event}接口的事件
     * <li> 功能相较于{@link Dispatcher#dispatch(EventRegistry, Object)}更为丰富
     * <li> 如果事件实现了该接口, 则事件总线默认使用该方法完成事件的调度.
     * @param eventRegistry
     * @param event
     */
    void dispatchEvent(EventRegistry eventRegistry, Event event);

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
