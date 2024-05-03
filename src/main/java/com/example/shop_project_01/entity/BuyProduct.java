package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
// 소비자용
public class BuyProduct {
// 구매 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_product_id")
    private Long buyProductId;
    
    //구매 수량
    private Long count;
    
    //구매가격
    @Column(name = "price")
    private Long price;
    
    //상품 번호
    @Column(name = "product_id")
    private Long productId;
    
    //구매번호
    @Column(name = "buy_id")
    //joinColumn - buy
    private Long buyId;
}
