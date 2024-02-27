package com.serliunx.iceye;

import com.serliunx.iceye.core.bus.AsyncEventBus;
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
        AsyncEventBus asyncEventBus = new AsyncEventBus();
        asyncEventBus.registerListener(new EventListener());
        asyncEventBus.publish(new FileEvent());
    }
}
