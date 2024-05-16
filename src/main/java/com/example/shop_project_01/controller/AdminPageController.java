package com.example.shop_project_01.controller;

import ch.qos.logback.core.status.Status;
import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.dto.BuyProductDto;
import com.example.shop_project_01.dto.NoticeDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.Buy;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.NoticeRepository;
import com.example.shop_project_01.service.AdminService;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
       @Autowired
       AdminService adminService;
       @Autowired
       UserAccountService userAccountService;
       
       @Autowired
       CartProductRepository cartProductRepository;
       
       @Autowired
       NoticeRepository noticeRepository;
       
       
       // =======================공지사항==========================
       
       //공지사항 등록하기
       @GetMapping("/notice_add")
       public String addNoticeView(Model model) {
              model.addAttribute("noticeDto", new NoticeDto());
              return "admin/new_notice";
       }
       
       @PostMapping("/notice_add")
       public String addNotice(@ModelAttribute("noticeDto") @Valid NoticeDto dto, BindingResult bindingResult) {
              if (bindingResult.hasErrors()) {
                     return "admin/new_notice";
              }
              adminService.addNotice(dto);
              return "redirect:/admin/notice_all";
       }
       
       //공지사항 목록 확인(관리자용)
       @GetMapping("/notice_all")
       public String showAllNotice(Model model) {
              List<NoticeDto> dtoList = adminService.showAllNotice();
              long count = dtoList.size();
              model.addAttribute("count", count);
              model.addAttribute("dtoList", dtoList);
              return "admin/notice_all";
       }
       
       //공지사항 하나씩 확인하기
       @GetMapping("/notice_change/{noticeId}")
       public String noticeChange(@PathVariable("noticeId") Long noticeId, Model model) {
              NoticeDto noticeDto = adminService.noticeViewFindById(noticeId);
              
              model.addAttribute("noticeDto", noticeDto);
              return "admin/notice_change";
       }
       
       //공지사항 삭제하기
       @PostMapping("/notice_delete/{deleteId}")
       public String deleteNotice(@PathVariable("deleteId") Long noticeId,
                                  RedirectAttributes redirectAttributes) {
              
              adminService.deleteNotice(noticeId);
              redirectAttributes.addFlashAttribute("msg", "공지사항이 정상적으로 삭제되었습니다!!");
              return "redirect:/admin/notice_all";
       }
       
       //공지사항 수정하기
       @GetMapping("/notice_update")
       public String noticeUpdateView(@RequestParam("noticeId") Long noticeId,
                                      Model model) {
              NoticeDto noticeDto = adminService.noticeViewFindById(noticeId);
              model.addAttribute("noticeDto", noticeDto);
              return "admin/notice_updateForm";
       }
       
       
       @PostMapping("/notice_update")
       public String noticeUpdate(@ModelAttribute("noticeDto") @Valid NoticeDto dto,
                                  BindingResult bindingResult) {
              if (bindingResult.hasErrors()) {
                     return "admin/notice_updateForm";
              }
              adminService.updateNotice(dto);
              return "redirect:/admin/notice_all";
       }
    
    
    
    // =======================판매목록==========================
       @GetMapping("/sales_all")
       public String salesAll(Model model) {
              List<BuyProductDto> dto = adminService.showSalesAll();
              long count = dto.size();
              
              model.addAttribute("count", count);
              model.addAttribute("dto", dto);
              return "/admin/sales_all";
       }
       
       @GetMapping("/sales_all/deliver")
       public String salesAllWithDeliver(Model model) {
              List<BuyProductDto> dto = adminService.showSalesAll();
              long count = dto.size();
              
              model.addAttribute("count", count);
              model.addAttribute("dto", dto);
              return "/admin/sales_all";
       }
    
    @GetMapping("/sales_status")
    public String salesStatus(Model model) {
        List<BuyProductDto> dto = adminService.showSalesAll();
        long count = dto.size();
        
           // 상태 값 목록 생성
           List<String> statusValues = new ArrayList<>();
           statusValues.add("입금완료");
           statusValues.add("배송중");
           statusValues.add("배송완료");
//           List<ProductStatus> statusValues = Arrays.asList(ProductStatus.values());
        
        model.addAttribute("statusValues",statusValues);
        model.addAttribute("count", count);
        model.addAttribute("dto", dto);
        return "/admin/sales_status";
    }
    
    @PostMapping("/updateProductStatus")
    public String updateProductStatus(@RequestParam("status")String status,
                                                               @RequestParam("buyProductId")Long buyProductId){
           System.out.println(buyProductId);
           System.out.println(status);
           adminService.updateProductStatus(buyProductId,status);
           return "redirect:/admin/sales_status";
    }
    
    
    
    // =======================상품목록==========================
       @GetMapping("/product_all")
       public String productViewAll(Model model) {
              List<ProductDto> dto = adminService.productViewAll();
              long count = dto.stream().count();
              model.addAttribute("count", count);
              model.addAttribute("dto", dto);
              return "/admin/product_all";
       }
    
    @GetMapping("/product_change/{id}")
    public String productChange(@PathVariable("id") Long productId, Model model) {
        ProductDto productDto = adminService.productViewFindById(productId);
        
        model.addAttribute("product", productDto);
        return "admin/product_change";
    }
    
    // 상품등록하기
    @GetMapping("/product_add")
    public String addProductView(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "admin/new_product";
    }
    
    @PostMapping("/product_add")
    public String addProduct(@ModelAttribute("productDto") ProductDto dto) {
        adminService.addProduct(dto);
        return "redirect:/admin/product_all";
    }
    
    
    //상품 정보 수정하기
    @GetMapping("/product_update")
    public String productUpdateView(@RequestParam("productId") Long productId,
                                    Model model) {
        ProductDto productDto = adminService.productViewFindById(productId);
        model.addAttribute("productDto", productDto);
        return "admin/product_updateForm";
    }
    
    
    @PostMapping("/product_update")
    public String update(@ModelAttribute("productDto") ProductDto dto
    ) {
        adminService.updateProduct(dto);
        return "redirect:/admin/product_all";
    }
    
    
    //상품 삭제하기
    @PostMapping("/delete/{deleteId}")
    public String deleteProduct(@PathVariable("deleteId") Long productId,
                                RedirectAttributes redirectAttributes) {
        
        
        // 사용자의 모든 장바구니에서 관리자가 삭제하기를 선택한 해당 상품을 삭제
        List<CartProduct> cartProducts = cartProductRepository.findByProduct_ProductId(productId);
        for (CartProduct cartProduct : cartProducts) {
            cartProductRepository.delete(cartProduct);
        }
        
        // 그리고 나서 상품 삭제
        adminService.deleteProduct(productId);
        
        redirectAttributes.addFlashAttribute("msg", "물건 삭제가 정상적으로 처리되었습니다!!");
        return "redirect:/admin/product_all";
    }
    
    
    // =======================회원목록==========================
       @GetMapping("/user_all")
       public String showAllUser(Model model) {
              List<UserAccountDto> dtoList = adminService.showAllUserExceptAdmin();
              long count = dtoList.size();
              model.addAttribute("count", count);
              model.addAttribute("dtoList", dtoList);
              return "admin/user_all";
       }
       

       
       
       @GetMapping("/user_change/{username}")
       public String productChange(@PathVariable("username") String username, Model model) {
              UserAccountDto userAccountDto = userAccountService.getMyPage(username);
              model.addAttribute("userAccountDto", userAccountDto);
              return "admin/user_change";
       }
       
       //회원정보 수정하기
       @GetMapping("/update")
       public String updateAccount(@RequestParam("username") String username,
                                   Model model) {
              UserAccountDto accountDto = adminService.getUserPage(username);


//        System.out.println(accountDto.toString());
              
              model.addAttribute("accountDto", accountDto);
              return "/admin/user_updateForm";
       }
       
       
       @PostMapping("/update")
       public String update(@RequestParam("name") String name,
                            @RequestParam("userPhone") String userPhone,
                            @RequestParam("userEmail") String userEmail,
                            @RequestParam("userAddress") String userAddress,
                            BindingResult bindingResult) {
              if (bindingResult.hasErrors()) {
                     return "/admin/user_updateForm";
              }
              UserAccountDto userAccountDto = new UserAccountDto(name, userPhone, userEmail, userAddress);
              
              adminService.updateUser(userAccountDto);// 사용자 정보 업데이트 메서드를 호출해야 함
              
              return "redirect:/admin/user_all";
       }
       
       
       //회원 강제 탈퇴
       @PostMapping("/userDelete/{deleteId}")
       public String deleteUser(@PathVariable("deleteId") String username,
                                RedirectAttributes redirectAttributes) {
              
              userAccountService.deleteAccount(username);
              redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 정상적으로 처리되었습니다!!");
              return "redirect:/admin/user_all";
       }
       


}
