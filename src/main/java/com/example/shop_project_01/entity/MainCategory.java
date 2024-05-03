package com.example.shop_project_01.entity;

import jakarta.persistence.*;

@Entity
public class MainCategory {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "category_id")
       private Long categoryId;
       
       private String mainCategory;
}
