package com.serliunx.iceye.core.dispatcher;

import com.serliunx.iceye.core.EventRegistry;

/**
 * 包装了一个调度器的随机调度器(?
 * @author SerLiunx
 * @since 1.0
 */
public class DefaultWeightedDispatcher implements WeightDispatcher {

    private final Dispatcher dispatcher;
    private int weight;

    /**
     * @param dispatcher 调度器
     * @param weight 权重, 必须大于0, 如不设置则默认为 1
     */
    public DefaultWeightedDispatcher(Dispatcher dispatcher, int weight) {
        if(!validateWeight(weight)){
            throw new IllegalArgumentException(String.format("The weight is only allowed to be set from %s to %s but found %s",
                    MIN_WEIGHT, MAX_WEIGHT, weight));
        }
        this.dispatcher = dispatcher;
        this.weight = weight;
    }

    public DefaultWeightedDispatcher(Dispatcher dispatcher) {
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

    @Override
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private boolean validateWeight(int weight){
        return weight >= MIN_WEIGHT;
    }
}
