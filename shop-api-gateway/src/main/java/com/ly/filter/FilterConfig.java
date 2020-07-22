package com.ly.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
public class FilterConfig {

    @Bean
    @Order(1)
    public GlobalFilter firstGlobalFilter() {
        log.info("[first][pre]");
        return new AuthGlobalFilter();
    }
}
