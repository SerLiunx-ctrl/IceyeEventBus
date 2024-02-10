package com.serliunx.eventbus.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据包装
 * @author SerLiunx
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class Element<T> {

    /**
     * 数据对象
     */
    private T data;
    /**
     * 数据有效时限(毫秒为单位)
     */
    private long expiredTime;
}
