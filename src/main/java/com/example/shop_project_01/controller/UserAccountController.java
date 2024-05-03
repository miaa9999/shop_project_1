package com.example.shop_project_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserAccountController {
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

}
