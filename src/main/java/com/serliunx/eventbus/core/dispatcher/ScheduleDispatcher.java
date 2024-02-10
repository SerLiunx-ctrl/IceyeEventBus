package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.EventRegistry;

/**
 * 定时事件调度器
 * <li> 简易用法说明:
 * <p> 当一个事件指定为定时事件调度时、可以根据你所设置的定时时间、间隔调度事件
 * @author SerLiunx
 * @since 1.0
 */
public class ScheduleDispatcher implements Dispatcher{

    //

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {

    }

    @Override
    public boolean validateContext(EventRegistry eventRegistry, Object event) {
        return Dispatcher.super.validateContext(eventRegistry, event);
    }
}
