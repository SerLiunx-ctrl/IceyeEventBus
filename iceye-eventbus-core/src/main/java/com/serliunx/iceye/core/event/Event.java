package com.serliunx.iceye.core.event;

import java.util.concurrent.TimeUnit;

/**
 * 事件定义接口
 * <li> 对事件做一些具体设置, 仅针对事件本身.
 * @author SerLiunx
 * @since 1.0
 */
public interface Event {

    /**
     * 该事件的最大存活次数
     * <li> 当有多个监听器监听了该事件时, 可能被多次消费(监听), 通过改参数可以限制最大次数
     * <li> 返回 -1 时代表不限制次数
     */
    default int maxLives(){
        return -1;
    }

    /**
     * 该任务的存活时间
     */
    default long getTimeAlive(){
        return -1;
    }

    /**
     * 任务存活时间单位
     */
    default TimeUnit getTimeUnit(){
        return TimeUnit.MILLISECONDS;
    }
}
