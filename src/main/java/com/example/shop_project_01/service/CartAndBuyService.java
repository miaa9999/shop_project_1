package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.CartRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    EntityManager em;

    //유저아이디로 유저의 카트아이디 가져오기
    public Long cartIdFindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    //장바구니에 담기
    public void addCartProduct(CartProductDto dto) {

        Cart cart = em.find(Cart.class, dto.getCartId());
        Product product = em.find(Product.class, dto.getProductId());
        List<CartProduct> cartProductList = cartProductRepository.findByCart_CartId(dto.getCartId());
//        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        int ifAddCount = dto.getCount();
        boolean productChange = false;

        //카트에 중복된 물건이 있을경우 수량을 더하여 카트에 다시 저장
        for (CartProduct cartProduct : cartProductList) {
            if (cartProduct.getProduct().getProductId().equals(dto.getProductId())) {
                ifAddCount += cartProduct.getCount();
                cartProduct.setCount(ifAddCount);
                cartProductRepository.save(cartProduct);
                productChange = true;
                break;

            }
        }
        //카트에 중복된 물건이 없을경우 카트에 새로 저장
            if (!productChange) {
                CartProduct cartProduct = new CartProduct();
                ifAddCount = dto.getCount();
                cartProduct.setCount(ifAddCount);
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);

                cartProductRepository.save(cartProduct);
            }
        }

    //유저의 전체 장바구니 가져오기
    public List<CartProduct> showMyCart(String username) {
        Cart cart = cartRepository.findByUserAccount_Username(username);
        List<CartProduct> cartProducts = cartProductRepository.findByCart_CartId(cart.getCartId());

        return cartProducts;
    }



}