package com.example.shop_project_01.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
       
       @GetMapping("/")
       public String main(){
              return "main";
       }
       
}
