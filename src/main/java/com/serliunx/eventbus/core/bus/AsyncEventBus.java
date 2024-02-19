package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.dispatcher.AsyncDispatcher;

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
    public void publish(Object event) {
        asyncDispatcher.autoDispatch(eventRegistry, event);
    }

    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return subscribeAnnotation.async();
    }
}
