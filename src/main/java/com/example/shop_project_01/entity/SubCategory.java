package com.example.shop_project_01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SubCategory {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "sub_category_id")
       private Long subCategoryId;
       
       //main categoryId; (join)
       private Long CategoryId;
       private String subCategory;
}
