package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.dispatcher.AsyncDispatcher;
import com.serliunx.eventbus.core.dispatcher.Dispatcher;

import java.lang.reflect.Method;

/**
 * 异步事件总线
 * <li> 默认使用异步调度器
 * @author SerLiunx
 * @since 1.0
 */
public class AsyncEventBus extends AbstractEventBus{

    private final Dispatcher asyncDispatcher;

    public AsyncEventBus(Dispatcher asyncDispatcher) {
        this.asyncDispatcher = asyncDispatcher;
    }

    public AsyncEventBus() {
        this(new AsyncDispatcher());
    }

    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return subscribeAnnotation.async();
    }

    @Override
    public void publish(Object event) {
        asyncDispatcher.dispatch(super.eventRegistry, event);
    }
}
