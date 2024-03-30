package com.serliunx.iceye.core.limiter;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class DispatcherTrafficLimiter extends AbstractTrafficLimiter{

    public DispatcherTrafficLimiter(int capacity, long interval) {
        super(capacity, interval);
    }

    @Override
    protected void beforeSleep() {
        System.out.println("线程开始睡眠");
    }

    @Override
    protected void afterSleep() {
        System.out.println("线程睡眠结束");
    }
}
