package com.example.shop_project_01.controller;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.dto.*;
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

import java.time.LocalDateTime;

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
    
    @PostMapping("/product_detail/cart_and_buy")
    public String addCart(
                          @RequestParam("price")int price,
                          @RequestParam("count")int count,
                          @RequestParam("productId")Long productId,
                          @RequestParam("loginUsername")String loginUsername,
                          @RequestParam("action")String action,
                          Model model
                          ) {
        Long userCartNum = cartAndBuyService.cartIdFindByUsername(loginUsername);

        if (action.equals("cart")) {
            if(userCartNum!=null) {
                CartProductDto cartProductDto = new CartProductDto(count, productId, price, userCartNum);
                cartAndBuyService.addCartProduct(cartProductDto);
            }
            return "redirect:/cart";
            
        } else if (action.equals("buy")) {
          //구매하기 버튼 눌렀을때 작동
            String productName = cartAndBuyService.productNameFindByProductId(productId);
            int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
            
            BuyProductDto buyDto = new BuyProductDto(count,price,productId,productName);
            int total = price * count;
            
            System.out.println(productName);
            System.out.println(count);
            
            model.addAttribute("username",loginUsername);
            model.addAttribute("userPoint",userPoint);
            model.addAttribute("total",total);
            model.addAttribute("buyDto",buyDto);
            return "/cart/buy_one";
        } else {
            return "redirect:/product_detail/" + productId;
        }
    }
    
    @PostMapping("/buy_one")
    public String buyOne(@RequestParam("action")String action,
                         @RequestParam("productName")String productName,
                         @RequestParam("productId")Long productId,
                         @RequestParam("username")String loginUsername,
                         @RequestParam("count")int count,
                         @RequestParam("price")int nowPrice,
                         Model model
                         ){
        if (action.equals("cancel")){
            System.out.println(productName);
            System.out.println(productId);
            return  "redirect:/product_detail/" + productId;
            
        } else if (action.equals("buy")) {
            LocalDateTime buyDate = LocalDateTime.now();
            BuyDto buyDto = new BuyDto(buyDate,loginUsername,ProductStatus.DEPOSIT);
            BuyProductDto buyProductDto = new BuyProductDto(count,nowPrice,productId);
            cartAndBuyService.addBuyProductOne(buyDto,buyProductDto);
            
            return "cart/buy_ok";
        } else if (action.equals("requestMoney")) {
            int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
            model.addAttribute("productId",productId);
            model.addAttribute("userPoint",userPoint);
            model.addAttribute("username",loginUsername);
            return "/cart/insert_point";
        }
        
        return "/product/main";
    }
}
