package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.annotation.Subscribe;
import com.serliunx.iceye.core.EventRegistry;
import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.dispatcher.Dispatcher;
import com.serliunx.iceye.core.dispatcher.SyncDispatcher;
import com.serliunx.iceye.core.event.Event;
import com.serliunx.iceye.exception.TooManyParametersException;
import com.serliunx.iceye.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * 抽象事件总线实现
 * @author SerLiunx
 * @since 1.0
 */
public abstract class AbstractEventBus implements EventBus{

    protected final EventRegistry eventRegistry = new EventRegistry();
    private final Dispatcher dispatcher = new SyncDispatcher();

    @Override
    public final void registerListener(Listener listener) {
        Class<? extends Listener> clazz = listener.getClass();
        List<Method> methods = ReflectionUtils.getMethodsWithAnnotation(clazz, Subscribe.class);

        methods.stream()
                .filter(m -> m.getParameters().length > 0)
                .forEach(m -> {
                    Parameter[] parameters = m.getParameters();
                    if(parameters.length > 1){
                        String message = String.format("too many parameters, expect 1 but found %s, in %s (%s)",
                                parameters.length, m.getName(), clazz.getName());

                        throw new TooManyParametersException(message);
                    }
                    Subscribe annotation = m.getAnnotation(Subscribe.class);
                    Parameter parameter = parameters[0];
                    Class<?> eventClass = parameter.getType();
                    if(filter(m, annotation)){
                        eventRegistry.add(eventClass, m, listener);
                    }
                });
    }

    @Override
    public void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    @Override
    public final void publish(Object event) {
        if(event instanceof Event){
            Event e = (Event) event;
            publishEvent(e);
            return;
        }
        publishObject(event);
    }

    /**
     * 发布事件 - 普通事件
     * @param event 事件对象
     */
    protected void publishObject(Object event){
        dispatcher.dispatch(eventRegistry, event);
    }

    /**
     * 发布事件 - {@link Event}事件
     * @param event 事件对象
     */
    protected void publishEvent(Event event){
        dispatcher.dispatch(eventRegistry, event);
    }

    /**
     * 事件注册过滤逻辑, 请根据具体的事件总线来实现
     */
    protected abstract boolean filter(Method listenerMethod, Subscribe subscribeAnnotation);
}
