package com.serliunx.eventbus;

import com.serliunx.eventbus.core.bus.RandomEventBus;
import com.serliunx.eventbus.core.dispatcher.AsyncDispatcher;
import com.serliunx.eventbus.core.dispatcher.Dispatcher;
import com.serliunx.eventbus.core.dispatcher.SyncDispatcher;
import com.serliunx.eventbus.testutil.EventListener;
import com.serliunx.eventbus.testutil.FileEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class BusTest {

    @Test
    public void test(){
        RandomEventBus eventBus = new RandomEventBus();
        eventBus.registerListener(new EventListener());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(16), new ThreadFactory() {
            final AtomicInteger i = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new  Thread(r, "test-" + i.getAndIncrement());
            }
        });

        Dispatcher dispatcher = new AsyncDispatcher(threadPoolExecutor);

        eventBus.addDispatcher(new AsyncDispatcher(), 5);
        eventBus.addDispatcher(new SyncDispatcher(), 5);
        eventBus.addDispatcher(dispatcher, 100);

        eventBus.publish(new FileEvent());
    }
}
