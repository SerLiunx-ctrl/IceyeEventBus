package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.core.EventRegistry;
import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.annotation.Subscribe;
import com.serliunx.iceye.core.dispatcher.Dispatcher;
import com.serliunx.iceye.core.dispatcher.SyncDispatcher;
import com.serliunx.iceye.core.event.Event;
import com.serliunx.iceye.core.util.AnnotationMethodFilter;
import com.serliunx.iceye.core.util.CandidateMethodScanner;
import com.serliunx.iceye.core.util.DefaultCandidateMethodScanner;
import com.serliunx.iceye.core.util.ParametersCountMethodFilter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;

/**
 * 抽象事件总线实现
 * @author SerLiunx
 * @since 1.0
 */
public abstract class AbstractEventBus implements EventBus{

    protected final EventRegistry eventRegistry = new EventRegistry();
    private final Dispatcher dispatcher = new SyncDispatcher();
    protected CandidateMethodScanner candidateMethodScanner;

    public AbstractEventBus() {
        this.candidateMethodScanner =
                new DefaultCandidateMethodScanner(new AnnotationMethodFilter(Subscribe.class), true);
        candidateMethodScanner.addMethodFilter(new ParametersCountMethodFilter(1, 1));
    }

    @Override
    public void registerListener(Listener listener) {
        Class<? extends Listener> clazz = listener.getClass();
        List<Method> methods = candidateMethodScanner.getCandidateMethods(clazz);
        methods.forEach(m -> {
            Parameter[] parameters = m.getParameters(); //默认只会有一个参数
            Subscribe annotation = m.getAnnotation(Subscribe.class);
            Parameter parameter = parameters[0];
            Class<?> eventClass = parameter.getType();
            if(filter(m, annotation)){
                eventRegistry.add(eventClass, m, listener);
            }
        });
    }

    @Override
    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    @Override
    public void registerListeners(Collection<? extends Listener> listeners) {
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

    @Override
    public final void setCandidateMethodScanner(CandidateMethodScanner scanner) {
        this.candidateMethodScanner = scanner;
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
