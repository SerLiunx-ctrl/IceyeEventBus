package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.MethodHolder;
import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.event.Event;

import java.util.List;

/**
 * 同步事件调度器
 * @author SerLiunx
 * @since 1.0
 */
public class SyncDispatcher implements Dispatcher{

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {
        if(!validateContext(eventRegistry, event)){
            return;
        }
        List<MethodHolder> syncListeners = eventRegistry.getSubscribers().get(event.getClass());
        syncListeners.forEach(m -> {
            try {
                m.getMethod().invoke(m.getListener(), event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void dispatchEvent(EventRegistry eventRegistry, Event event) {
        if(!validateContext(eventRegistry, event)){
            return;
        }
        System.out.println("sync dispatch event");
    }
}
