package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的方法扫描器
 * @author SerLiunx
 * @since 1.0
 */
public class DefaultCandidateMethodScanner implements CandidateMethodScanner{

    protected final MethodFilter methodFilter;
    protected final boolean isPublic;

    /**
     * @param methodFilter 方法过滤器
     * @param isPublic 是否只扫描公共方法
     */
    public DefaultCandidateMethodScanner(MethodFilter methodFilter, boolean isPublic) {
        this.methodFilter = methodFilter;
        this.isPublic = isPublic;
    }

    @Override
    public List<Method> getCandidateMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        return Arrays.stream(methods)
                .filter(methodFilter::doFilter)
                .collect(Collectors.toList());
    }
}
