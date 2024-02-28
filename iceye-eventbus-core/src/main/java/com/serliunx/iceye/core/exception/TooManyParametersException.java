package com.serliunx.iceye.core.exception;

/**
 * 异常: 参数过多
 * @author SerLiunx
 * @since 1.0
 * @deprecated 扫描机制调整、方法过滤器现在不会报该异常
 */
@Deprecated
public class TooManyParametersException extends RuntimeException{
    public TooManyParametersException(String message) {
        super(message);
    }
}
