package com.example.shop_project_01.service;

import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.CartRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

    private final UserAccountRepository userAccountRepository;

    public CartAndBuyService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }


    public List<CartProduct> showMyCart(String username) {
        // 사용자의 username으로 해당 사용자 정보를 조회합니다.
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        // 사용자가 존재하고 장바구니가 비어있지 않다면 장바구니에 담긴 상품 목록을 반환합니다.
        if (userAccount != null && userAccount.getCart() != null) {
            return userAccount.getCart().getCartProducts();
        } else {
            // 사용자가 존재하지 않거나 장바구니가 비어있는 경우 빈 리스트를 반환합니다.
            return Collections.emptyList();
        }
    }

    }

