package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyProductRepository extends JpaRepository<BuyProduct,Long> {
}
