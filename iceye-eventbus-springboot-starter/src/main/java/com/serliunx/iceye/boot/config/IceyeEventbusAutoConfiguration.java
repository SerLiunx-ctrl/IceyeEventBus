package com.serliunx.iceye.boot.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author SerLiunx
 * @since 1.0
 */
@AutoConfiguration
@ConfigurationPropertiesScan(basePackageClasses = IceyeEventbusConfiguration.class)
public class IceyeEventbusAutoConfiguration {
}
