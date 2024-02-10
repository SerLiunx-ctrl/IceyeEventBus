package com.serliunx.eventbus.collection;

import java.util.LinkedList;

/**
 * @author SerLiunx
 * @since 1.0
 */
public class DefaultTimedStack<T> implements TimedStack<T>{

    private final LinkedList<Element<T>> stack = new LinkedList<>();

    public void push(T data, long time){
        Element<T> element = new Element<>(data, time);
        stack.addFirst(element);
    }

    private void removeExpiredElements() {
        long currentTime = System.currentTimeMillis();
        while (!stack.isEmpty() && stack.getLast().getExpiredTime() < currentTime) {
            stack.removeLast();
        }
    }
}
