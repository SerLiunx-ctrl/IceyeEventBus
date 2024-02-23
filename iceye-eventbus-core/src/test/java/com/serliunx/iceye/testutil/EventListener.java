package com.serliunx.iceye.testutil;

import com.serliunx.iceye.annotation.Subscribe;
import com.serliunx.iceye.core.Listener;

/**
 * 测试用事件监听器
 * @author SerLiunx
 * @since 1.0
 */
public class EventListener implements Listener {

    @Subscribe(async = true)
    public void handleFileEvent(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " >>> " + fileEvent);
    }

    @Subscribe(async = true)
    public void handleUserEvent(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " (2) >>> " + fileEvent);
    }
}
