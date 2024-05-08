package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartAndBuyController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartAndBuyService cartAndBuyService;


    @GetMapping("/product_detail/{productId}")
    public String product_detail(@PathVariable("productId")Long productId, Model model) {
        ProductDto product = categoryService.productViewOne(productId);
        CartProduct cartProduct = new CartProduct();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        model.addAttribute("loginUsername",username);
        model.addAttribute("cart", cartProduct);
        model.addAttribute("product",product);
        return "/product/product_detail";
    }

    @GetMapping("/cart/add_one")
    public String cartAddOneGet() {
        return "redirect:/";
    }

    @PostMapping("/cart/add_one")
    public String cartAddOne(@RequestParam("productId")Long productId,
                             @RequestParam("productPrice")int productPrice,
                             @RequestParam("productStock")int count,
                             @RequestParam("username")String username
                             ){
        Long cartId = cartAndBuyService.cartIdfindByUsername(username);

        CartProductDto cartProductDto = new CartProductDto(
                count,productId,productPrice,cartId
        );
        cartAndBuyService.addCartProduct(cartProductDto);

        return "redirect:/";
//        return "mypage/cart";
    }
}
