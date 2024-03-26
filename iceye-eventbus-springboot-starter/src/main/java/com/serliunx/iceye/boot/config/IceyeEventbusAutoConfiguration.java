package com.serliunx.iceye.boot.config;

import com.serliunx.iceye.boot.bean.EventListenerBeanDefinitionRegistryPostProcessor;
import com.serliunx.iceye.core.bus.EventBus;
import com.serliunx.iceye.core.bus.IntegratedEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
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
        return new IntegratedEventBus();
    }
}
