package com.ly.order.client.fallback;

import com.ly.domain.Product;
import com.ly.order.client.ProductServiceClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 使用FallbackFactory可在容错中拿到具体的错误信息
 */
@Service
@Slf4j
public class ProductServiceClientFallBackFactory implements FallbackFactory<ProductServiceClient> {

    @Override
    public ProductServiceClient create(Throwable throwable) {
        return new ProductServiceClient() {
            @Override
            public Product findById(Integer pid) {
                log.error("{}", throwable);
                Product product = new Product();
                product.setPid(-2);
                product.setPname("client fallbackfactory");
                return product;
            }
        };
    }
}
