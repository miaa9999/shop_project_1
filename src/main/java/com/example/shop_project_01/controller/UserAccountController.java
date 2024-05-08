package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserAccountController {

    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(UserAccountDto userAccountDto) {
        return "login";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "/myPage/mypage";
    }


    @GetMapping("signup")
    public String signUp(UserAccountDto userAccountDto) {
        return "signup";
    }

    @PostMapping("signup")
    private String signup(@Valid UserAccountDto userAccountDto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!userAccountDto.getPassword().equals(userAccountDto.getPasswordCheck())) {
            bindingResult.rejectValue
                    ("passwordCheck", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        try {
            userService.createUser(userAccountDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject
                    ("signupFailed", "이미 등록된 사용자 입니다.");
            return "/user/signup";
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "/user/signup";
        }
        return "redirect:/";
    }

}