package com.serliunx.eventbus.core.bus;

import com.serliunx.eventbus.annotation.Subscribe;
import com.serliunx.eventbus.core.dispatcher.Dispatcher;
import com.serliunx.eventbus.core.dispatcher.WeightedDispatcher;
import com.serliunx.eventbus.core.event.Event;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 随机事件总线
 * <li> 以一定的分配几率,使用不同的调度器来传递、执行监听器
 * @author SerLiunx
 * @since 1.0.0
 */
public class RandomEventBus extends AbstractEventBus{

    /**
     * 调度器集合
     */
    private final List<WeightedDispatcher> dispatchers = new CopyOnWriteArrayList<>();

    /**
     * 向集合中添加新的调度器
     * @param dispatcher 调度器
     */
    @SuppressWarnings("all")
    public boolean addDispatcher(Dispatcher dispatcher, int weight){
        WeightedDispatcher weightedDispatcher = new WeightedDispatcher(dispatcher, weight);
        return dispatchers.add(weightedDispatcher);
    }

    /**
     * 扫描所有监听器
     */
    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return true;
    }

    @Override
    public void publish(Object event) {
        Dispatcher dispatcher = apportionDispatcher();
        if(dispatcher == null) return;
        if(event instanceof Event){
            Event e = (Event) event;
            dispatcher.dispatchEvent(eventRegistry, e);
        }else {
            dispatcher.dispatch(eventRegistry, event);
        }
    }

    /**
     * 获取所有调度器
     * @return 调度器
     */
    public List<WeightedDispatcher> getDispatchers() {
        return dispatchers;
    }

    /**
     * 根据权重分配调度器
     * @return 调度器
     */
    private Dispatcher apportionDispatcher(){
        int totalWeight = 0;

        for (WeightedDispatcher dispatcher : dispatchers) {
            totalWeight += dispatcher.getWeight();
        }

        Random random = new Random();
        int result = random.nextInt(totalWeight);

        for (WeightedDispatcher dispatcher : dispatchers) {
            result -= dispatcher.getWeight();
            if (result <= 0) {
                return dispatcher;
            }
        }
        return dispatchers.get(dispatchers.size() - 1);
    }
}
