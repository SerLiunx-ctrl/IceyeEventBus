package com.serliunx.iceye.core.limiter;

import java.util.function.Consumer;

/**
 * 事件流量限制器
 * @author SerLiunx
 * @since 1.0
 */
public interface TrafficLimiter {

    void submit(Runnable runnable);

    <T extends Throwable> void submit(Runnable runnable, Consumer<T> afterThrows);

    long getInterval();

    int getConcurrentTaskCount();
}
