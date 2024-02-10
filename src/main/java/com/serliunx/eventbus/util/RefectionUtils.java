package com.serliunx.eventbus.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射相关工具类
 * @author SerLiunx
 * @since 1.0
 */
public final class RefectionUtils {

    private RefectionUtils(){throw new UnsupportedOperationException();}

    /**
     * 获取标注了指定注解的方法(只获取public方法)
     * @param targetClass 类
     * @param annotationClass 注解
     * @return 方法
     */
    public static List<Method> getMethodsWithAnnotation(Class<?> targetClass, Class<? extends Annotation> annotationClass){
        return getMethodsWithAnnotation(targetClass, annotationClass, true);
    }

    /**
     * 获取标注了指定注解的方法
     * @param targetClass 类
     * @param annotationClass 注解
     * @param isPublic 是否只获取public的方法
     * @return 方法
     */
    public static List<Method> getMethodsWithAnnotation(Class<?> targetClass,
                                                        Class<? extends Annotation> annotationClass, boolean isPublic){

        Method[] methods = isPublic ? targetClass.getMethods() : targetClass.getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(m -> m.getAnnotation(annotationClass) != null)
                .peek(m -> m.setAccessible(true))
                .collect(Collectors.toList());
    }
}
