package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.Weighted;

/**
 * 包装了一个调度器的随机调度器(?
 * @author SerLiunx
 * @since 1.0
 */
public class WeightedDispatcher implements Dispatcher, Weighted {

    /**
     * 最大权重 Integer.MAX_VALUE
     */
    public static final int MAX_WEIGHT = Integer.MAX_VALUE;
    /**
     * 最小权重值: 1
     */
    public static final int MIN_WEIGHT = 1;
    private final Dispatcher dispatcher;
    private int weight;

    public WeightedDispatcher(Dispatcher dispatcher, int weight) {
        if(!validateWeight(weight)){
            throw new IllegalArgumentException(String.format("The weight is only allowed to be set from %s to %s but found %s",
                    MIN_WEIGHT, MAX_WEIGHT, weight));
        }
        this.dispatcher = dispatcher;
        this.weight = weight;
    }

    public WeightedDispatcher(Dispatcher dispatcher) {
        this(dispatcher, 1);
    }

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {
        dispatcher.dispatch(eventRegistry, event);
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private boolean validateWeight(int weight){
        return weight >= MIN_WEIGHT;
    }
}
