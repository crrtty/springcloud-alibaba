package com.ly.order.client;

import com.ly.domain.Product;
import com.ly.order.client.fallback.ProductServiceClientFallBack;
import com.ly.order.client.fallback.ProductServiceClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        value = "shop-product" ,
        //fallback = ProductServiceClientFallBack.class,
        fallbackFactory = ProductServiceClientFallBackFactory.class
)
public interface ProductServiceClient {

    @RequestMapping("/product/get/{pid}")
    Product findById(@PathVariable("pid") Integer pid);
}
