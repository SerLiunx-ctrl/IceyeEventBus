package com.serliunx.iceye;

import com.serliunx.iceye.core.bus.EventBus;
import com.serliunx.iceye.core.bus.SyncEventBus;
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
        EventBus eventBus = new SyncEventBus();

        eventBus.registerListener(new EventListener());
        eventBus.publish(new FileEvent());
    }
}
