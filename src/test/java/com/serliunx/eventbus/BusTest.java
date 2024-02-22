package com.serliunx.eventbus;

import com.serliunx.eventbus.core.bus.RandomEventBus;
import com.serliunx.eventbus.core.dispatcher.AsyncDispatcher;
import com.serliunx.eventbus.core.dispatcher.SyncDispatcher;
import com.serliunx.eventbus.testutil.EventListener;
import com.serliunx.eventbus.testutil.FileEvent;
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
