package com.ly.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ly.domain.Order;
import com.ly.order.blockhandler.OrderServiceBlockHandler;
import com.ly.order.dao.OrderDao;
import com.ly.order.fallback.OrderServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ly.order.service.OrderService;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    private int count = 0;

    //@SentinelResource(value = "create",blockHandler = "createOrderBlockHandler",fallback = "createOrderFallback")
    @SentinelResource(value = "create",
            blockHandlerClass = OrderServiceBlockHandler.class,blockHandler = "createOrderBlockHandler",
            fallbackClass = OrderServiceFallback.class,fallback = "createOrderFallback")
    @Override
    public Order createOrder(Order order) {
        count++;
        if (count % 3 == 0){
            throw new RuntimeException();
        }
        return orderDao.save(order);
    }

    /*public Order createOrderBlockHandler(Order order, BlockException e){
        log.info("触发了BlockException，内容为={}", e);
        order.setUid(-1);
        order.setUsername("BlockException");
        return order;
    }*/

    /*public Order createOrderFallback(Order order, Throwable e){
        log.info("触发了Throwable，内容为={}", e);
        order.setUid(-2);
        order.setUsername("Throwable");
        return order;
    }*/
}
