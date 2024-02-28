package com.serliunx.iceye.boot.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;

import java.util.Set;

/**
 *
 * @author SerLiunx
 * @since 1.0
 */
public class EventListenerBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor,
        EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry)
            throws BeansException {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        String basePackage = environment.getProperty("iceye-eventbus.listener.base-package");
        if(basePackage == null || basePackage.isEmpty()){
            return;
        }
        Set<BeanDefinition> definitions = scanner.findCandidateComponents(basePackage);
        for (BeanDefinition definition : definitions) {

        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
            throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
