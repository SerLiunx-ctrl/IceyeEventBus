package com.serliunx.iceye.core.constant;

/**
 * 事件总线类型, 系统中默认的集中事件总线实现
 * <li> {@link com.serliunx.iceye.core.bus.AsyncEventBus} - 异步事件总线
 * <li> {@link com.serliunx.iceye.core.bus.SyncEventBus} - 同步事件总线
 * <li> {@link com.serliunx.iceye.core.bus.RandomEventBus} - 随机事件总线
 * @author SerLiunx
 * @since 1.0
 */
public enum EventBusType {

    /**
     * 异步事件总线
     */
    ASYNC,

    /**
     * 同步事件总线
     */
    SYNC,

    /**
     * 随机事件总线
     */
    RANDOM;
}
