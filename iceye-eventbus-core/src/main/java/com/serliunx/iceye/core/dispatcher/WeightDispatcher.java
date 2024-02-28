package com.serliunx.iceye.core.dispatcher;

import com.serliunx.iceye.core.Weighted;

/**
 * 带权重的事件调度器
 * @author SerLiunx
 * @since 1.0
 */
public interface WeightDispatcher extends Dispatcher, Weighted {

    /**
     * 最大权重 Integer.MAX_VALUE
     */
    int MAX_WEIGHT = Integer.MAX_VALUE;

    /**
     * 最小权重值: 1
     */
    int MIN_WEIGHT = 1;

    /**
     * 获取包装的事件调度器
     */
    Dispatcher getDispatcher();
}
