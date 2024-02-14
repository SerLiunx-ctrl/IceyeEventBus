package com.serliunx.eventbus;

import com.serliunx.eventbus.core.bus.AsyncEventBus;
import com.serliunx.eventbus.core.bus.EventBus;
import com.serliunx.eventbus.testutil.EventListener;
import com.serliunx.eventbus.testutil.FileEvent;
import com.serliunx.eventbus.testutil.OtherListener;
import com.serliunx.eventbus.testutil.UserEvent;
import org.junit.jupiter.api.Test;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class BusTest {

    @Test
    public void test(){
        EventBus eventBus = new AsyncEventBus();
        eventBus.registerListener(new EventListener());
        eventBus.registerListener(new OtherListener());
        eventBus.publish(new UserEvent());
        eventBus.publish(new FileEvent());
    }
}
