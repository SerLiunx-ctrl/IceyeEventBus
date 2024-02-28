package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;

/**
 * 方法过滤器(控制参数个数)
 * @author SerLiunx
 * @since 1.0
 */
public class ParametersCountMethodFilter implements MethodFilter{

    /**
     * 最小参数个数(包含)
     */
    private final int minParametersCount;

    /**
     * 最大参数个数(包含)
     */
    private final int maxParametersCount;

    public ParametersCountMethodFilter(int minParametersCount, int maxParametersCount) {
        this.minParametersCount = minParametersCount;
        this.maxParametersCount = maxParametersCount;
    }

    @Override
    public boolean doFilter(Method method) {
        int length = method.getParameters().length;
        return length >= minParametersCount && length <= maxParametersCount;
    }
}
