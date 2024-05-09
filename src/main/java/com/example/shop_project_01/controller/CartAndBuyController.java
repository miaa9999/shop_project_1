package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.CategoryService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    
    @GetMapping("/product_detail/{productId}")
    public String product_detail(@PathVariable("productId") Long productId,
                                 Model model) {
        ProductDto product = categoryService.productViewOne(productId);
//        CartProductDto cartProduct = new CartProductDto();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("지금이 여기야 1번지점       "+ "유저이름 : " + username);
        
        model.addAttribute("loginUsername", username);
//        model.addAttribute("cart", cartProduct);
        model.addAttribute("product", product);
        return "/product/product_detail";
    }
    
    @PostMapping("/cart")
    public String addCart(
//                          @RequestParam("product")ProductDto productDto,
                          @RequestParam("price")int price,
                          @RequestParam("count")int count,
                          @RequestParam("productId")Long productId,
                          @RequestParam("loginUsername")String loginUsername
                          ){
        System.out.println("가격 : "+ price);
        System.out.println("구매수량 : "+ count);
        System.out.println("상품번호 : "+ productId);
        System.out.println("유저아이디 : "+ loginUsername);
        log.info("여기가 2번지점이야");
        Long userCartNum = cartAndBuyService.cartIdFindByUsername(loginUsername);
        
        CartProductDto cartProductDto = new CartProductDto(count,productId,price,userCartNum);
        cartAndBuyService.addCartProduct(cartProductDto);
        System.out.println(cartProductDto);
    return "redirect:/";
    }
}
