package com.serliunx.iceye.core.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池相关工具类
 * @author SerLiunx
 * @since 1.0
 */
public final class ExecutorTools {
    private ExecutorTools(){throw new UnsupportedOperationException();}

    /**
     * 获取一个CPU密集型线程池
     * <li> 适用于CPU占用时间短、瞬时流量大的场景
     * <li> 阻塞队列实现采用的是{@link ArrayBlockingQueue}
     * @param time 非核心线程的存活时间
     * @param timeUnit 存活时间单位
     * @param queueSize 县城等待队列的大小
     * @see ArrayBlockingQueue
     * @return CPU密集型线程池
     */
    public static ThreadPoolExecutor newCpuIntensiveThreadPool(long time, TimeUnit timeUnit, int queueSize){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(availableProcessors, availableProcessors * 2, time, timeUnit,
                new ArrayBlockingQueue<>(queueSize));
    }

    /**
     * 获取一个CPU密集型线程池
     * <li> 适用于CPU占用时间短、瞬时流量大的场景
     * <li> 阻塞队列实现采用的是{@link ArrayBlockingQueue}
     * @param time 非核心线程的存活时间
     * @param timeUnit 存活时间单位
     * @return CPU密集型线程池
     */
    public static ThreadPoolExecutor newCpuIntensiveThreadPool(long time, TimeUnit timeUnit){
        return newCpuIntensiveThreadPool(time, timeUnit, Runtime.getRuntime().availableProcessors());
    }

    /**
     * 获取一个CPU密集型线程池
     * <li> 适用于CPU占用时间短、瞬时流量大的场景
     * <li> 阻塞队列实现采用的是{@link ArrayBlockingQueue}
     * @return CPU密集型线程池
     */
    public static ThreadPoolExecutor newCpuIntensiveThreadPool(){
        return newCpuIntensiveThreadPool(5, TimeUnit.SECONDS);
    }
}
