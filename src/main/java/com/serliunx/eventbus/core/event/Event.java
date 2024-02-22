package com.serliunx.eventbus.core.event;

import java.util.concurrent.TimeUnit;

/**
 * 事件定义接口
 * <li> 对事件做一些具体设置, 仅针对事件本身.
 * @author SerLiunx
 * @since 1.0
 */
public interface Event {

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
