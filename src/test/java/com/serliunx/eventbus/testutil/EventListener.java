package com.serliunx.eventbus.testutil;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.Listener;

/**
 * 测试用事件监听器
 * @author SerLiunx
 * @since 1.0
 */
public class EventListener implements Listener {

    @Subscribe(async = true)
    public void handleFileEvent(FileEvent fileEvent){

    }

    @Subscribe(async = true)
    public void handleUserEvent(UserEvent userEvent){
        System.out.println(Thread.currentThread().getName() + ">>> " + userEvent);
    }
}
