package com.weikbest.pro.saas.common.event;

import org.springframework.context.ApplicationEvent;


/**
 * 泛型事件
 *
 * @author 甘之萌  2023/05/04 15:38
 */
public class GenericEvent<T>{
    private final T data;
    public GenericEvent(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
}
