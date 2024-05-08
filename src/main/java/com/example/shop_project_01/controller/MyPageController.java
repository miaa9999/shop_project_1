package com.example.shop_project_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
       
       @GetMapping("/mypage")
       public String myPage() {
              return "/myPage/mypage";
       }
       
       @GetMapping("/admin_page")
       public String adminPage() {
              return "/myPage/admin_page";
       }
}
