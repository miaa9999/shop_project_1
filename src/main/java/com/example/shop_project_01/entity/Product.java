package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//상품정보
public class Product {
    
    //상품 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    
    //상품 이름
    @Column(name = "product_name")
    private String productName;
    
    //상품 가격
    @Column(name = "product_price")
    private int productPrice;
    
    //상품 재고
    @Column(name = "product_stock")
    private int productStock;
    
    private String mainCategoryName;
    
    private String subCategoryName;
//    @ManyToOne
//    @JoinColumn(name = "mainCategoryName")
//    private MainCategory mainCategory;
//
//    @ManyToOne
//    @JoinColumn(name = "mainCategoryName")
//    private SubCategory subCategory;
    //게시판
    private String content;

    private String imgUrl;

}
