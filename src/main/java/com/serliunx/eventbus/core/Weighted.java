package com.serliunx.eventbus.core;

/**
 * 权重接口
 * @author SerLiunx
 * @since 1.0
 */
public interface Weighted {

    /**
     * 获取当前对象的权重值
     */
    int getWeight();

    /**
     * 设置当前对象的权重值
     */
    void setWeight(int weight);
}
