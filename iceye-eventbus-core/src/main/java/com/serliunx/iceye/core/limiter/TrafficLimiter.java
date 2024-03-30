package com.serliunx.iceye.core.limiter;

import java.util.function.Consumer;

/**
 * 限流器
 * @author SerLiunx
 * @since 1.0
 */
public interface TrafficLimiter {

    /**
     * 提交一个任务
     * @param runnable 任务
     */
    void submit(Runnable runnable);

    <T extends Throwable> void submit(Runnable runnable, Consumer<T> afterThrows);

    long getInterval();

    int getConcurrentTaskCount();

    void shutdown();
}
