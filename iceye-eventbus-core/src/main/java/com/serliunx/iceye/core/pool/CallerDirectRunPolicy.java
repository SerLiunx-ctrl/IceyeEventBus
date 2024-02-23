package com.serliunx.iceye.core.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝策略 - 调用方直接运行, 忽略线程池的状态
 * @author SerLiunx
 * @since 1.0
 */
public class CallerDirectRunPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        r.run();
    }
}
