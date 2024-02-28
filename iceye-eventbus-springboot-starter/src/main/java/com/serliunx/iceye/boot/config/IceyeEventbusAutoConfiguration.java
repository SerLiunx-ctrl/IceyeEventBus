package com.serliunx.iceye.boot.config;

import com.serliunx.iceye.boot.bean.EventListenerBeanDefinitionRegistryPostProcessor;
import com.serliunx.iceye.core.bus.AsyncEventBus;
import com.serliunx.iceye.core.bus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SerLiunx
 * @since 1.0
 */
@Configuration
public class IceyeEventbusAutoConfiguration {

    @Bean
    public EventListenerBeanDefinitionRegistryPostProcessor eventListenerBeanDefinitionRegistryPostProcessor(){
        return new EventListenerBeanDefinitionRegistryPostProcessor();
    }

    @Bean
    public EventBus defaultEventBus(){
        return new AsyncEventBus();
    }
}
