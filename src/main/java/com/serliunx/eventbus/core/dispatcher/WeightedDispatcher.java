package com.serliunx.eventbus.core.dispatcher;

import com.serliunx.eventbus.core.EventRegistry;
import com.serliunx.eventbus.core.Weighted;
import com.serliunx.eventbus.core.event.Event;

/**
 * 包装了一个调度器的随机调度器(?
 * @author SerLiunx
 * @since 1.0
 */
public class WeightedDispatcher implements Dispatcher, Weighted {

    /**
     * 最大权重: 1000
     */
    public static final int MAX_WEIGHT = 1000;
    private final Dispatcher dispatcher;
    private int weight;

    public WeightedDispatcher(Dispatcher dispatcher, int weight) {
        if(!validateWeight(weight)){
            throw new IllegalArgumentException(String.format("The weight is only allowed to be set from %s to %s but found %s",
                    0, MAX_WEIGHT, weight));
        }
        this.dispatcher = dispatcher;
        this.weight = weight;
    }

    public WeightedDispatcher(Dispatcher dispatcher) {
        this(dispatcher, 0);
    }

    @Override
    public void dispatch(EventRegistry eventRegistry, Object event) {
        dispatcher.dispatch(eventRegistry, event);
    }

    @Override
    public void dispatchEvent(EventRegistry eventRegistry, Event event) {
        dispatcher.dispatchEvent(eventRegistry, event);
    }

    private boolean validateWeight(int weight){
        return weight >= 0 && weight <= MAX_WEIGHT;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
