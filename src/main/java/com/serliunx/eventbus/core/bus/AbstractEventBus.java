package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.Listener;
import com.serliunx.eventbus.core.dispatcher.Dispatcher;
import com.serliunx.eventbus.core.dispatcher.SyncDispatcher;
import com.serliunx.eventbus.exception.TooManyParametersException;
import com.serliunx.eventbus.util.ReflectionUtils;

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
    public void registerListener(Listener listener) {
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
    public void publish(Object event) {
        dispatcher.autoDispatch(eventRegistry, event);
    }

    /**
     * 事件注册过滤逻辑, 请根据具体的事件总线来实现
     */
    protected abstract boolean filter(Method listenerMethod, Subscribe subscribeAnnotation);
}
