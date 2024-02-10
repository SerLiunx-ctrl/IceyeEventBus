package com.serliunx.eventbus.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * 方法 - 对象
 * @author SerLiunx
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class MethodHolder {
    private Method method;
    private Listener listener;
}
