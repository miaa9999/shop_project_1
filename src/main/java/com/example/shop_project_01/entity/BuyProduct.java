package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BuyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_product_id")
    private Long buyProductId;
    private Long count;
    @Column(name = "price")
    private Long price;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "buy_id")
    private Long buyId;
}
