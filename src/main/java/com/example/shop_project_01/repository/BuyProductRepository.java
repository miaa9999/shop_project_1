package com.example.shop_project_01.repository;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.entity.BuyProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyProductRepository extends JpaRepository<BuyProduct,Long> {
       Page<BuyProduct> findByBuy_ProductStatus(ProductStatus productStatus, Pageable pageable);
       List<BuyProduct> findByBuy_Username(String username);
}
