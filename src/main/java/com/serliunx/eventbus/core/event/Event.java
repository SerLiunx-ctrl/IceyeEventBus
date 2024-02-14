package com.serliunx.eventbus.core.event;

import com.serliunx.eventbus.annotation.Subscribe;

import java.util.concurrent.TimeUnit;

/**
 * 事件定义接口
 * <li> 对事件做一些具体设置, 仅针对事件本身.
 * <li> 与{@link Subscribe}的区别在哪:
 * <p> 1. 参数针对事件本身、且优先生效
 * <p> 2. {@link Subscribe} 的参数设置针对指定的监听方法所监听的事件
 * @author SerLiunx
 * @since 1.0
 */
public interface Event {

    //TODO 2024.2.10 功能待完善

    default boolean isAsync(){
        return false;
    }

    /**
     * 设置该任务的存活时间
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
