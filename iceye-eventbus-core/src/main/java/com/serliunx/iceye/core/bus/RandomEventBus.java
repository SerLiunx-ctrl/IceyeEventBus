package com.serliunx.iceye.core.bus;

import com.serliunx.iceye.core.annotation.Subscribe;
import com.serliunx.iceye.core.dispatcher.DefaultWeightedDispatcher;
import com.serliunx.iceye.core.dispatcher.Dispatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
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
    private final List<DefaultWeightedDispatcher> dispatchers = new CopyOnWriteArrayList<>();

    public RandomEventBus(DefaultWeightedDispatcher...dispatchers) {
        this.dispatchers.addAll(Arrays.asList(dispatchers));
    }

    public RandomEventBus(Collection<? extends DefaultWeightedDispatcher> dispatchers) {
        this.dispatchers.addAll(dispatchers);
    }

    public RandomEventBus() {
    }

    /**
     * 向集合中添加新的调度器
     * @param dispatcher 调度器
     */
    @SuppressWarnings("all")
    public boolean addDispatcher(Dispatcher dispatcher, int weight){
        DefaultWeightedDispatcher defaultWeightedDispatcher = new DefaultWeightedDispatcher(dispatcher, weight);
        return dispatchers.add(defaultWeightedDispatcher);
    }

    /**
     * 扫描所有监听器
     */
    @Override
    protected boolean filter(Method listenerMethod, Subscribe subscribeAnnotation) {
        return true;
    }

    @Override
    protected void publishObject(Object event) {
        Dispatcher dispatcher = apportionDispatcher();
        if(dispatcher == null) return;
        dispatcher.dispatch(eventRegistry, event);
    }

    /**
     * 根据权重分配调度器
     * @return 调度器
     */
    private Dispatcher apportionDispatcher(){
        if(dispatchers.isEmpty()){
            return null;
        }
        int totalWeight = 0;

        for (DefaultWeightedDispatcher dispatcher : dispatchers) {
            totalWeight += dispatcher.getWeight();
        }

        //如果全部调度器的权重都相等
        if(totalWeight / dispatchers.size() == dispatchers.get(0).getWeight()){
            int index = new Random(System.currentTimeMillis()).nextInt(dispatchers.size());
            return dispatchers.get(index);
        }

        Random random = new Random(System.currentTimeMillis());
        int result = random.nextInt(totalWeight);

        for (DefaultWeightedDispatcher dispatcher : dispatchers) {
            result -= dispatcher.getWeight();
            if (result <= 0) {
                return dispatcher;
            }
        }
        return dispatchers.get(dispatchers.size() - 1);
    }
}
