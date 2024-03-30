package com.serliunx.iceye;

import com.serliunx.iceye.core.limiter.DispatcherTrafficLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class LimiterTest {

    public static final Logger log = LoggerFactory.getLogger(LimiterTest.class);

    public static void main(String[] args) {
        DispatcherTrafficLimiter trafficLimiter = new DispatcherTrafficLimiter(1, 400);

    }
}
