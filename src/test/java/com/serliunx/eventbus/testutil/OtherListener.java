package com.serliunx.eventbus.testutil;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.Listener;

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
