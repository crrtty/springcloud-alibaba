package com.ly.order.blockhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ly.domain.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceBlockHandler {

    public static Order createOrderBlockHandler(Order order, BlockException e){
        log.info("触发了BlockException，内容为={}", e);
        order.setUid(-1);
        order.setUsername("BlockException");
        return order;
    }
}
