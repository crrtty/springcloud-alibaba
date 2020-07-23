package com.ly.product.controller;

import com.alibaba.fastjson.JSON;
import com.ly.domain.Product;
import com.ly.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productrService;

    @RequestMapping("/api1/demo1")
    public String apiDemo1(){
        return "demo1";
    }

    @RequestMapping("/api1/demo2")
    public String apiDemo2(){
        return "demo2";
    }

    @RequestMapping("/api2/demo3")
    public String apiDemo3(){
        return "demo3";
    }

    @RequestMapping("/api2/demo4")
    public String apiDemo4(){
        return "demo1";
    }


    @RequestMapping("/get/{pid}")
    public Product get(@PathVariable("pid") Integer pid){
        log.info("查询{}商品信息",pid);
        Product product = productrService.findById(pid);
        log.info("商品信息内容为{}", JSON.toJSONString(product));
        return product;
    }
}
