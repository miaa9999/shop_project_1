package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "product_stock")
    private int productStock;
    private String content;


}
