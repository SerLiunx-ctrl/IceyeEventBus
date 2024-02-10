package com.serliunx.eventbus.core.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事件调度线程工厂
 * @author SerLiunx
 * @since 1.0
 */
public class EventDispatcherThreadFactory implements ThreadFactory {

    private static final String THEAD_NAME_PATTERN = "event-listener-%s";
    private final AtomicInteger count = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format(THEAD_NAME_PATTERN, count.getAndIncrement()));
    }
}
