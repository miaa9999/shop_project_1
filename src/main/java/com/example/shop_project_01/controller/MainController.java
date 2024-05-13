package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.NoticeDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.NoticeRepository;
import com.example.shop_project_01.service.CategoryService;
import com.example.shop_project_01.service.NoticeService;
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
       @Autowired
       NoticeService noticeService;
       @GetMapping("/")
       public String main() {
              return "/product/main";
       }
       
       @GetMapping("/admin_page")
       public String adminPage() {
              return "/admin/admin_page";
       }

       //공지사항 페이지
       @GetMapping("/notice")
       public String noticePage(Model model){
              List<NoticeDto> dtoList = noticeService.showAllNotice();
              long count = dtoList.size();
              model.addAttribute("count", count);
              model.addAttribute("dtoList", dtoList);
              return "/notice/notice_all";
       }

       //공지사항 제목 링크를 클릭하면 내용확인
       @GetMapping("/notice_showOne/{noticeId}")
       public String noticeChange(@PathVariable("noticeId")Long noticeId, Model model){
              NoticeDto noticeDto = noticeService.noticeViewFindById(noticeId);
              model.addAttribute("noticeDto",noticeDto);
              return "/notice/notice_showOne";
       }




       


}

