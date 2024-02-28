package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;

/**
 * 方法名前缀过滤
 * <li> 例: 扫描所有以handle开头的方法
 * @author SerLiunx
 * @since 1.0
 */
public class MethodNamePrefixMethodFilter implements MethodFilter{

    private final String methodPrefix;

    public MethodNamePrefixMethodFilter(String methodPrefix) {
        this.methodPrefix = methodPrefix;
    }

    @Override
    public boolean doFilter(Method method) {
        return method.getName().startsWith(methodPrefix);
    }
}
