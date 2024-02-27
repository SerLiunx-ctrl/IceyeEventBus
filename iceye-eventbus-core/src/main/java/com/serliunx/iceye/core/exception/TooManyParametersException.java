package com.serliunx.iceye.core.exception;

/**
 * 异常: 参数过多
 * @author SerLiunx
 * @since 1.0
 */
public class TooManyParametersException extends RuntimeException{
    public TooManyParametersException(String message) {
        super(message);
    }
}
