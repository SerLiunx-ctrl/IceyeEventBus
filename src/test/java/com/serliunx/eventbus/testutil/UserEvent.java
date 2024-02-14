package com.serliunx.eventbus.testutil;

import com.serliunx.eventbus.core.event.Event;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class UserEvent implements Event {

    @Override
    public long getTimeAlive() {
        return 3000;
    }
}
