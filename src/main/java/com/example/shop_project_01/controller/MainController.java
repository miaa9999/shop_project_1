package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.service.CategoryService;
import com.example.shop_project_01.service.UserAccountService;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
       @Autowired
       CategoryService categoryService;
       @Autowired
       UserAccountService userAccountService;
       
       @GetMapping("/")
       public String main() {
              return "/product/main";
       }
       
       @GetMapping("/admin_page")
       public String adminPage() {
              return "/admin/admin_page";
       }


       


}

