package com.serliunx.iceye.core.util;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法扫描器
 * @author SerLiunx
 * @since 1.0
 */
public interface CandidateMethodScanner {

    /**
     * 获取条件适配的方法
     * @return 候选方法
     */
    List<Method> getCandidateMethods(Class<?> clazz);

    /**
     * 添加一个方法过滤器
     * @param methodFilter 方法过滤器
     */
    void addMethodFilter(MethodFilter methodFilter);
}
