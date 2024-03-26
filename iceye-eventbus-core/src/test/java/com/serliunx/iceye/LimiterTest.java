package com.serliunx.iceye;

import com.serliunx.iceye.core.limiter.DispatcherTrafficLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class LimiterTest {

    public static final Logger log = LoggerFactory.getLogger(LimiterTest.class);

    public static void main(String[] args) {
        DispatcherTrafficLimiter trafficLimiter = new DispatcherTrafficLimiter(1, 400);

        for (int i = 0; i < 2; i++) {
            final int count = i;
            final long time = 300L;
            trafficLimiter.submit(() -> {
                log.info("一个耗时{}毫秒的任务 => {}", time, count);
                try {
                    TimeUnit.MILLISECONDS.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, t -> {

            });
        }
    }
}
