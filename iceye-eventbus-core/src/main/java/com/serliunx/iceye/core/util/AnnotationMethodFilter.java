package com.serliunx.iceye.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 方法过滤器 - 指定注解
 * @author SerLiunx
 * @since 1.0
 */
public class AnnotationMethodFilter implements MethodFilter{

    protected final Class<? extends Annotation> annotationClass;

    public AnnotationMethodFilter(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    @Override
    public boolean doFilter(Method method) {
        return method.getAnnotation(annotationClass) != null;
    }
}
