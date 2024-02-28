package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的方法扫描器
 * @author SerLiunx
 * @since 1.0
 */
public class DefaultCandidateMethodScanner implements CandidateMethodScanner{

    protected final Collection<MethodFilter> methodFilters;
    protected final boolean isPublic;

    /**
     * @param methodFilter 方法过滤器
     * @param isPublic 是否只扫描公共方法
     */
    public DefaultCandidateMethodScanner(MethodFilter methodFilter, boolean isPublic) {
        this.methodFilters = new HashSet<>();
        this.methodFilters.add(methodFilter);
        this.isPublic = isPublic;
    }

    @Override
    public List<Method> getCandidateMethods(Class<?> clazz) {
        Method[] methods = isPublic ? clazz.getMethods() : clazz.getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(this::filter)
                .collect(Collectors.toList());
    }

    @Override
    public void addMethodFilter(MethodFilter methodFilter){
        this.methodFilters.add(methodFilter);
    }

    protected boolean filter(Method method){
        for (MethodFilter methodFilter : methodFilters) {
            boolean result = methodFilter.doFilter(method);
            if(!result){
                return false;
            }
        }
        return true;
    }
}
