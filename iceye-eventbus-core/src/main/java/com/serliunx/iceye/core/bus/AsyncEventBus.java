package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.annotation.Subscribe;
import com.serliunx.iceye.core.dispatcher.AsyncDispatcher;
import com.serliunx.iceye.core.event.Event;

import java.lang.reflect.Method;

/**
 * 异步事件总线
 * <li> 默认使用异步调度器
 * @author SerLiunx
 * @since 1.0
 */
public class AsyncEventBus extends AbstractEventBus{

    private final AsyncDispatcher asyncDispatcher;

    public AsyncEventBus(AsyncDispatcher asyncDispatcher) {
        this.asyncDispatcher = asyncDispatcher;
    }

    public AsyncEventBus() {
        this(new AsyncDispatcher());
    }

    @Override
    protected void publishObject(Object event) {
        asyncDispatcher.dispatch(eventRegistry, event);
    }

    @Override
    protected void publishEvent(Event event) {
        asyncDispatcher.dispatch(eventRegistry, event);
    }

    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return subscribeAnnotation.async();
    }
}
