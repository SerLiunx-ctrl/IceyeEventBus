package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.dispatcher.AsyncDispatcher;
import com.serliunx.eventbus.core.dispatcher.Dispatcher;

import java.lang.reflect.Method;

/**
 * 异步事件总线
 * @author SerLiunx
 * @since 1.0
 */
public class AsyncEventBus extends AbstractEventBus{

    private final Dispatcher asyncDispatcher = new AsyncDispatcher();

    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return subscribeAnnotation.async();
    }

    @Override
    public void publish(Object event) {
        asyncDispatcher.dispatch(super.eventRegistry, event);
    }
}
