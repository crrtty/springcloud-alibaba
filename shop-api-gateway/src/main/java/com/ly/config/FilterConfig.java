package com.ly.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FilterConfig {

    /**
     * 认证鉴权 filter
     * @return
     */
    /*@Bean
    @Order(1)
    public GlobalFilter firstGlobalFilter() {
        log.info("[first][pre]");
        return new AuthGlobalFilter();
    }*/
}
