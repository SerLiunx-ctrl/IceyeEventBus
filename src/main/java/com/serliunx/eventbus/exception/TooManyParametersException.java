package com.serliunx.eventbus.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 异常: 参数过多
 * @author SerLiunx
 * @since 1.0
 */
@Getter
@Setter
public class TooManyParametersException extends RuntimeException{
    public TooManyParametersException(String message) {
        super(message);
    }
}
