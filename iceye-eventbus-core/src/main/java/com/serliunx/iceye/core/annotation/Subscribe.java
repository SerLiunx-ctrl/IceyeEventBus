package com.serliunx.iceye.core.annotation;

import com.serliunx.iceye.core.dispatcher.AsyncDispatcher;
import com.serliunx.iceye.core.dispatcher.SyncDispatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于订阅事件的注解
 * @author SerLiunx
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    /**
     * 是否异步监听事件
     * <li> 如果设置为异步执行, 将交由异步调度器调度{@link AsyncDispatcher}
     * <li> 否则将交由同步调度器调度{@link SyncDispatcher}
     */
    boolean async() default false;
}
