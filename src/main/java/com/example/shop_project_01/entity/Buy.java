package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long buyId;

    @CreatedDate
    @Column(name = "buy_date",updatable = false)
    private LocalDateTime buyDate;
    @Column(name = "user_id",length = 50)
    private String userId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
