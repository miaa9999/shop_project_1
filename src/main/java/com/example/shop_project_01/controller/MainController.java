package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
       @Autowired
       CategoryService categoryService;
       
       @GetMapping("/")
       public String main() {
              return "main";
       }
       
       @GetMapping("/doll")
       public String dollCategory(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> dollProducts = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getMainCategory().equals("인형")) {
                            dollProducts.add(product);
                     }
              }
              model.addAttribute("dollAll", dollProducts);
              return "/product/doll_all";
              
       }
}

