package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//장바구니 목록
public class CartProduct {
    
    //장바구니 고유 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long cartProductId;
    
    // 구매수량
    private int count;
    
    //유저의 장바구니 번호
    @Column(name = "cart_id")
    private Long cartId;
    
    //상품 번호
    @Column(name = "product_id")
    private Long productId;

}
