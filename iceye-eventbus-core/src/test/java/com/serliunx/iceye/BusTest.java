package com.serliunx.iceye;

import com.serliunx.iceye.core.bus.EventBus;
import com.serliunx.iceye.core.bus.IntegratedEventBus;
import com.serliunx.iceye.testutil.FileEvent;
import com.serliunx.iceye.testutil.OtherListener;
import com.serliunx.iceye.testutil.UserEvent;
import org.junit.jupiter.api.Test;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class BusTest {

    @Test
    public void test(){
        EventBus eventBus = new IntegratedEventBus();
        eventBus.registerListener(new OtherListener());
        eventBus.publish(new FileEvent());
        eventBus.publish(new UserEvent());
    }
}
