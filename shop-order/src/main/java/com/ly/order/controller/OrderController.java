package com.ly.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.ly.domain.Order;
import com.ly.domain.Product;
import com.ly.order.client.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ly.order.service.OrderService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductServiceClient productServiceClient;

    private int count = 0;

    @RequestMapping("/message")
    public String message(){
        return "message";
    }

    @RequestMapping("/message2")
    public String message2(){
        count++;
        if (count % 3 == 0){
            throw new RuntimeException();
        }
        return "message2";
    }

    @RequestMapping("/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age){
        return "message3:" + name + " ," + age;
    }

    @RequestMapping("/create/{pid}")
    public Order create(@PathVariable("pid") Integer pid){
        log.info("接收到{}商品下单信息",pid);
        //Product product = restTemplate.getForObject("http://localhost:8101/product/get/"+pid,Product.class);
        //Product product = restTemplate.getForObject("http://shop-product/product/get/"+pid,Product.class);
        Product product = productServiceClient.findById(pid);
        log.info("查询到商品信息内容为{}", JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试001");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        return orderService.createOrder(order);
    }

    @RequestMapping("/create/discovery/{pid}")
    public Order createDiscovery(@PathVariable("pid") Integer pid){
        log.info("接收到{}商品下单信息",pid);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("shop-product");
        ServiceInstance serviceInstance = serviceInstances.get(0);
        Product product = restTemplate.getForObject("http://"+serviceInstance.getHost()
                +":"+serviceInstance.getPort()+"/product/get/"+pid,Product.class);
        log.info("查询到商品信息内容为{}", JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试001");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        return orderService.createOrder(order);
    }
}
