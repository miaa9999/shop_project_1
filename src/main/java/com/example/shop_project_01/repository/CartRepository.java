package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserAccount(UserAccount userAccount);
}
