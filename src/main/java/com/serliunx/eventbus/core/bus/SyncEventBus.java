package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;

import java.lang.reflect.Method;

/**
 * 默认同步事件总线实现
 * <li> 使用默认的同步事件调度器
 * @author SerLiunx
 * @since 1.0
 */
public class SyncEventBus extends AbstractEventBus{

    /**
     * 定义扫描事件时是否忽略异步事件
     * <li> 默认不忽略, 即将异步事件通过同步的方式进行响应
     */
    private final boolean ignoreAsync;

    /**
     * @param ignoreAsync 定义扫描事件时是否忽略异步事件, 默认不忽略, 即将异步事件通过同步的方式进行响应
     */
    public SyncEventBus(boolean ignoreAsync) {
        this.ignoreAsync = ignoreAsync;
    }

    public SyncEventBus() {
        this(false);
    }

    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        if(ignoreAsync){
            return !subscribeAnnotation.async();
        }
        return true;
    }
}
