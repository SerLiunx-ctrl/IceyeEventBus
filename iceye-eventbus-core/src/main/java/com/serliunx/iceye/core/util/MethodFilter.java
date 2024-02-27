package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;

/**
 * 方法过滤器
 * @author SerLiunx
 * @since 1.0
 */
@FunctionalInterface
public interface MethodFilter {

    /**
     * 过滤指定方法
     * @param method 方法
     * @return 采用返回真, 否则返回假
     */
    boolean doFilter(Method method);
}
