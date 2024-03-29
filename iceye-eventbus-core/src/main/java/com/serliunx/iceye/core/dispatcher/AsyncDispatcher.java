package com.serliunx.iceye.core.dispatcher;

import com.serliunx.iceye.core.EventRegistry;
import com.serliunx.iceye.core.MethodHolder;
import com.serliunx.iceye.core.pool.CallerDirectRunPolicy;
import com.serliunx.iceye.core.pool.EventDispatcherThreadFactory;

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
                new ArrayBlockingQueue<>(16), new EventDispatcherThreadFactory("event-listener-%s"), new CallerDirectRunPolicy()));
    }

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {
        if(!validateContext(eventRegistry, event)){
            return;
        }
        List<MethodHolder> asyncListeners = eventRegistry.getSubscribers().get(event.getClass());
        asyncListeners.forEach(m -> poolExecutor.submit(() -> {
            try {
                m.getMethod().invoke(m.getListener(), event);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }));
    }

    public ThreadPoolExecutor getPoolExecutor() {
        return poolExecutor;
    }
}
