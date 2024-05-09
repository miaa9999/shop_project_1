package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
    List<CartProduct> findByCart_CartId(Long cartId);
}