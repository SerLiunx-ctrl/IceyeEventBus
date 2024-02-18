package com.serliunx.eventbus.support.boot;

import com.serliunx.eventbus.core.bus.EventBus;
import com.serliunx.eventbus.core.bus.SyncEventBus;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 配置文件
 * @author SerLiunx
 * @since 1.0
 */
@AutoConfiguration
public class IceyeEventBusAutoConfiguration {

    @Bean
    public EventBus defaultEventBus(){
        return new SyncEventBus(true);
    }
}
