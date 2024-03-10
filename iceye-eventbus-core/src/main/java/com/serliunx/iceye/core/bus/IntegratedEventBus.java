package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.core.EventRegistry;
import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.annotation.Subscribe;
import com.serliunx.iceye.core.dispatcher.AsyncDispatcher;
import com.serliunx.iceye.core.dispatcher.Dispatcher;
import com.serliunx.iceye.core.dispatcher.SyncDispatcher;
import com.serliunx.iceye.core.util.AnnotationMethodFilter;
import com.serliunx.iceye.core.util.CandidateMethodScanner;
import com.serliunx.iceye.core.util.DefaultCandidateMethodScanner;
import com.serliunx.iceye.core.util.ParametersCountMethodFilter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;

/**
 * 集成的事件总线
 * @author SerLiunx
 * @since 1.0
 */
public final class IntegratedEventBus implements EventBus{

    private final Dispatcher syncDispatcher = new SyncDispatcher();
    private final Dispatcher asyncDispatcher = new AsyncDispatcher();
    private final EventRegistry syncEventRegistry = new EventRegistry();
    private final EventRegistry asyncEventRegistry = new EventRegistry();
    private CandidateMethodScanner scanner;

    public IntegratedEventBus() {
        this.scanner =
                new DefaultCandidateMethodScanner(new AnnotationMethodFilter(Subscribe.class), true);
        scanner.addMethodFilter(new ParametersCountMethodFilter(1, 1));
    }

    @Override
    public void registerListener(Listener listener) {
        List<Method> candidateMethods = scanner.getCandidateMethods(listener.getClass());
        candidateMethods.forEach(m -> {
            Subscribe subscribe = m.getAnnotation(Subscribe.class);
            Parameter[] parameters = m.getParameters(); //默认只会有一个参数
            Parameter parameter = parameters[0];
            Class<?> eventClass = parameter.getType();
            if(subscribe.async()){
                asyncEventRegistry.add(eventClass, m, listener);
            }else{
                syncEventRegistry.add(eventClass, m, listener);
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
    public void publish(Object event) {
        Class<?> clazz = event.getClass();
        if(asyncEventRegistry.getSubscribers().containsKey(clazz)){
            asyncDispatcher.dispatch(asyncEventRegistry, event);
        }
        if(syncEventRegistry.getSubscribers().containsKey(clazz)){
            syncDispatcher.dispatch(syncEventRegistry, event);
        }
    }

    @Override
    public void setCandidateMethodScanner(CandidateMethodScanner scanner) {
        this.scanner = scanner;
    }

    public Dispatcher getSyncDispatcher() {
        return syncDispatcher;
    }

    public Dispatcher getAsyncDispatcher() {
        return asyncDispatcher;
    }

    public EventRegistry getSyncEventRegistry() {
        return syncEventRegistry;
    }

    public EventRegistry getAsyncEventRegistry() {
        return asyncEventRegistry;
    }

    public CandidateMethodScanner getScanner() {
        return scanner;
    }
}
