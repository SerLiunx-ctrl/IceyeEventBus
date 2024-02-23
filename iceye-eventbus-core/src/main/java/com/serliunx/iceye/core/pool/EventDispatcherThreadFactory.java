package com.serliunx.iceye.core.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事件调度线程工厂
 * @author SerLiunx
 * @since 1.0
 */
public class EventDispatcherThreadFactory implements ThreadFactory {

    private final String threadNamePattern;
    private final AtomicInteger count = new AtomicInteger(1);

    public EventDispatcherThreadFactory(String threadNamePattern) {
        this.threadNamePattern = threadNamePattern;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format(threadNamePattern, count.getAndIncrement()));
    }
}
