package com.ly.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_product")
@Data
public class Product {
    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    /**
     * 商品名称
     */
    private String pname;
    /**
     * 商品单价
     */
    private Double pprice;
    /**
     * 商品库存
     */
    private Integer stock;
}
