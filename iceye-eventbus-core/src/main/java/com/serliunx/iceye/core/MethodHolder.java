package com.serliunx.iceye.core;

import java.lang.reflect.Method;

/**
 * 方法 - 对象
 * @author SerLiunx
 * @since 1.0
 */
public class MethodHolder {
    private Method method;
    private Listener listener;

    public MethodHolder(Method method, Listener listener) {
        this.method = method;
        this.listener = listener;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
