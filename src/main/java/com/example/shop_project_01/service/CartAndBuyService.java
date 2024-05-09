package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartProductRepository cartProductRepository;

    public Long cartIdfindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    public void addCartProduct(CartProductDto cartProductDto) {
        CartProduct cartProduct = CartProductDto.fromCartProductDto(cartProductDto);
        cartProductRepository.save(cartProduct);
    }
}
