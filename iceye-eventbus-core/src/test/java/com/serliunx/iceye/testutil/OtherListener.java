package com.serliunx.iceye.testutil;

import com.serliunx.iceye.annotation.Subscribe;
import com.serliunx.iceye.core.Listener;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class OtherListener implements Listener {

    @Subscribe(async = true)
    public void fileEvent(FileEvent fileEvent){
        System.out.println(fileEvent);
    }
}
