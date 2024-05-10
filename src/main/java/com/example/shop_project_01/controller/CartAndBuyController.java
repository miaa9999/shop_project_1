package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.CategoryService;
import com.example.shop_project_01.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CartAndBuyController {


    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CartAndBuyService cartAndBuyService;

    @Autowired
    UserService userService;
    
    @GetMapping("/product_detail/{productId}")
    public String product_detail(@PathVariable("productId") Long productId,
                                 Model model) {
        ProductDto product = categoryService.productViewOne(productId);
        String username = userService.loginUsername();
        
        model.addAttribute("loginUsername", username);
        model.addAttribute("product", product);
        return "/product/product_detail";
    }
    
    @PostMapping("/product_detail/cart")
    public String addCart(
                          @RequestParam("price")int price,
                          @RequestParam("count")int count,
                          @RequestParam("productId")Long productId,
                          @RequestParam("loginUsername")String loginUsername
                          ){
        Long userCartNum = cartAndBuyService.cartIdFindByUsername(loginUsername);
if(userCartNum!=null) {
    CartProductDto cartProductDto = new CartProductDto(count, productId, price, userCartNum);
    cartAndBuyService.addCartProduct(cartProductDto);
}
    return "redirect:/product_detail/"+productId;
    }
}
