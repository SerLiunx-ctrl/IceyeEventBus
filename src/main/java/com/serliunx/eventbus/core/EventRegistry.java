package com.serliunx.eventbus.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件 - 订阅者
 * @author SerLiunx
 * @since 1.0
 */
public class EventRegistry {

    private final Map<Class<?>, List<MethodHolder>> subscribers = new ConcurrentHashMap<>();

    public void add(Class<?> eventClass, Method method, Listener listener){
        List<MethodHolder> methods = subscribers.get(eventClass);
        MethodHolder methodHolder = new MethodHolder(method, listener);

        if(methods == null || methods.isEmpty()){
            ArrayList<MethodHolder> arrayList = new ArrayList<>();
            arrayList.add(methodHolder);
            subscribers.put(eventClass, arrayList);
            return;
        }
        methods.add(methodHolder);
    }

    public Map<Class<?>, List<MethodHolder>> getSubscribers() {
        return subscribers;
    }
}
