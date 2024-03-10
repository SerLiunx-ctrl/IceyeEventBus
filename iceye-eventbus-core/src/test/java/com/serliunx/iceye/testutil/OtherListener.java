package com.serliunx.iceye.testutil;

import com.serliunx.iceye.core.Listener;
import com.serliunx.iceye.core.annotation.Subscribe;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class OtherListener implements Listener {

    @Subscribe(async = true)
    public void fileEvent0(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " ==> " + fileEvent);
    }

    @Subscribe
    public void userEvent(UserEvent userEvent){
        System.out.println(Thread.currentThread().getName() + " ==> " + userEvent);
    }

    @Subscribe(async = true)
    public void fileEvent1(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " ==> " + fileEvent);
    }

    @Subscribe
    public void fileEvent2(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " ==> " + fileEvent);
    }

    @Subscribe(async = true)
    public void fileEvent3(FileEvent fileEvent){
        System.out.println(Thread.currentThread().getName() + " ==> " + fileEvent);
    }

}
