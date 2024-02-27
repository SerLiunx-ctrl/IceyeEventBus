package com.serliunx.iceye.testutil;

import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.annotation.Subscribe;

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
}
