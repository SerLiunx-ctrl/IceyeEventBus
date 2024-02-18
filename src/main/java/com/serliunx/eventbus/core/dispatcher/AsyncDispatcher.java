package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.MethodHolder;
import com.serliunx.eventbus.core.event.Event;
import com.serliunx.eventbus.core.pool.CallerDirectRunPolicy;
import com.serliunx.eventbus.core.pool.EventDispatcherThreadFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步事件调度器
 * @author SerLiunx
 * @since 1.0
 */
public class AsyncDispatcher implements Dispatcher{

    private final ThreadPoolExecutor poolExecutor;

    public AsyncDispatcher(ThreadPoolExecutor poolExecutor) {
        this.poolExecutor = poolExecutor;

        Runtime.getRuntime().addShutdownHook(new Thread(poolExecutor::shutdown));
    }

    public AsyncDispatcher() {
        this(new ThreadPoolExecutor(16, 32, 60, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(16), new EventDispatcherThreadFactory(), new CallerDirectRunPolicy()));
    }

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {
        if(!validateContext(eventRegistry, event)){
            return;
        }
        List<MethodHolder> asyncListeners = eventRegistry.getSubscribers().get(event.getClass());
        asyncListeners.forEach(m -> {
            poolExecutor.submit(() -> {
                try {
                    m.getMethod().invoke(m.getListener(), event);
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Override
    public void dispatchEvent(EventRegistry eventRegistry, Event event) {

    }

    public ThreadPoolExecutor getPoolExecutor() {
        return poolExecutor;
    }
}
