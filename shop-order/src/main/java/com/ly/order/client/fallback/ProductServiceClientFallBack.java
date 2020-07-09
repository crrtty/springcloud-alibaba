package com.ly.order.client.fallback;

import com.ly.domain.Product;
import com.ly.order.client.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
public class ProductServiceClientFallBack implements ProductServiceClient {

    @Override
    public Product findById(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        product.setPname("client fallback");
        return product;
    }
}
