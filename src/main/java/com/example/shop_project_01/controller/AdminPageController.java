package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.service.AdminService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
       @Autowired
       AdminService adminService;
       @GetMapping("/product_all")
       public String productViewAll(Model model)
       {
              List<ProductDto> dto = adminService.productViewAll();
              long count = dto.stream().count();
              model.addAttribute("count",count);
              model.addAttribute("dto",dto);
              return "/admin/product_all";
       }
       
       @GetMapping("/product_change/{id}")
       public String productChange(@PathVariable("id")Long productId, Model model){
              ProductDto productDto = adminService.productViewFindById(productId);

              model.addAttribute("product",productDto);
              return "admin/product_change";
       }
       
       @GetMapping("product_add")
       public String productAdd(ProductDto productDto){
              return "/admin/new_product";
       }
}
