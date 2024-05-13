package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class CartController {
    @Autowired
    CartAndBuyService cartAndBuyService;

    @Autowired
    UserService userService;
    @GetMapping("/cart")
    public String showCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CartProduct> cartProducts = cartAndBuyService.showMyCart(username);
        List<CartProductDto> cartProductDtos = new ArrayList<>();

            int listSize = cartProducts.size();
            for (CartProduct cartProduct : cartProducts){
               CartProductDto cart = new CartProductDto();
               cart.setCartProductId(cartProduct.getCartProductId());
               cart.setCount(cartProduct.getCount());
               cart.setProductName(cartProduct.getProduct().getProductName());
               cart.setProductPrice(cartProduct.getProduct().getProductPrice());

               cartProductDtos.add(cart);
            }

//        cartProductDtos.forEach(x-> System.out.println(x));


        model.addAttribute("size",listSize);
        model.addAttribute("myCart",cartProductDtos);

        return "cart/my_cart_all";
    }

    @PostMapping("/cart/delete/{cartProductId}")
    public String cartAllModifyDelete(
            @PathVariable("cartProductId")Long cartProductId) {
        System.out.println(cartProductId);
        cartAndBuyService.cartProductDelete(cartProductId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/modify/{cartProductId}")
    public String cartAllModify(
            @PathVariable("cartProductId")Long cartProductId,
            @RequestParam("count")int count) {
        System.out.println("================" + count);
        cartAndBuyService.cartProductModifyCount(count,cartProductId);
        return "redirect:/cart";
    }
}
