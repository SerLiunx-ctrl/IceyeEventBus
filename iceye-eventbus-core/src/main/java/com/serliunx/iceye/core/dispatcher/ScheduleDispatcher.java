package com.serliunx.iceye.core.dispatcher;

import com.serliunx.iceye.core.EventRegistry;

/**
 * 定时事件调度器
 * <li> 简易用法说明:
 * <p> 当一个事件指定为定时事件调度时、可以根据你所设置的定时时间、间隔调度事件
 * @author SerLiunx
 * @since 1.0
 * @deprecated 该实现已废弃(弃坑), 职责过于重复。和定时任务框架重复度过高,
 * 如果需要使用, 建议使用事件作为触发器来触发定时任务.
 */
@Deprecated
public class ScheduleDispatcher implements Dispatcher{

    //TODO 完善功能

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {

    }
}
