package com.serliunx.iceye;

import com.serliunx.iceye.core.bus.RandomEventBus;
import com.serliunx.iceye.core.dispatcher.AsyncDispatcher;
import com.serliunx.iceye.core.dispatcher.SyncDispatcher;
import com.serliunx.iceye.testutil.EventListener;
import com.serliunx.iceye.testutil.FileEvent;
import org.junit.jupiter.api.Test;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class BusTest {

    @Test
    public void test(){
        RandomEventBus eventBus = new RandomEventBus();
        eventBus.registerListener(new EventListener());

        eventBus.addDispatcher(new AsyncDispatcher(), 1);
        eventBus.addDispatcher(new SyncDispatcher(), 1);

        eventBus.publish(new FileEvent());
    }
}
