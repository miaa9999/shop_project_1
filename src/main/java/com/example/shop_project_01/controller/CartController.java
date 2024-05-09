package com.example.shop_project_01.controller;

import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartAndBuyService cartAndBuyService;
    @GetMapping("/cart")
    public String showCart(Model model) {
        List<CartProduct> cartProducts = cartAndBuyService.showMyCart("username");

        if (cartProducts.isEmpty()) {
            model.addAttribute("emptyCartMessage", "장바구니가 비어있습니다.");
        } else {
            model.addAttribute("cartProducts", cartProducts);
        }

        return "cart/cart";
    }
}
