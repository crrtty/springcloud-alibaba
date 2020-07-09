package com.ly.order.fallback;

import com.ly.domain.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceFallback {

    public static Order createOrderFallback(Order order, Throwable e){
        log.info("触发了Throwable，内容为={}", e);
        order.setUid(-2);
        order.setUsername("Throwable");
        return order;
    }
}
