package com.example.shop_project_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OuterController {
       
       @GetMapping("/outer")
       public String outerViewMain(){
              return "outer/outer_view";
       }
       
}
