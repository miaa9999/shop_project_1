package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.CartRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    EntityManager em;

    public Long cartIdFindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    //장바구니에 담기
    public void addCartProduct(CartProductDto dto) {
        System.out.println("==============="+dto);
        CartProduct cartProduct = new CartProduct();
      
        Cart cart = em.find(Cart.class, dto.getCartId());
        Product product = em.find(Product.class, dto.getProductId());
        
        cartProduct.setProductPrice(dto.getProductPrice());
        cartProduct.setCount(dto.getCount());
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
//        System.out.println(cart);
//        em.find(Product.class,cartProductDto.getProductId());
        cartProductRepository.save(cartProduct);
    }
    
    
}
