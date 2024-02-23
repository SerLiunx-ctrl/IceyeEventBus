package com.serliunx.iceye.core.dispatcher;

import com.serliunx.iceye.core.EventRegistry;
import com.serliunx.iceye.core.MethodHolder;

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
}
