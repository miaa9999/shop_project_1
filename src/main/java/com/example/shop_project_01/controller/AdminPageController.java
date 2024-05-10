package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.service.AdminService;
import com.example.shop_project_01.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


       @GetMapping("/product_all")
       public String productViewAll(Model model) {
              List<ProductDto> dto = adminService.productViewAll();
              long count = dto.stream().count();
              model.addAttribute("count",count);
              model.addAttribute("dto",dto);
              return "/admin/product_all";
       }
    @GetMapping("/user_all")
    public String showAllUser(Model model) {
        List<UserAccountDto> dtoList = adminService.showAllUserExceptAdmin();
        long count = dtoList.size();
        model.addAttribute("count", count);
        model.addAttribute("dtoList", dtoList);
        return "admin/user_all";
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


    @GetMapping("/user_change/{username}")
    public String productChange(@PathVariable("username")String username, Model model){
        UserAccountDto userAccountDto = userAccountService.getMyPage(username);
        model.addAttribute("userAccountDto",userAccountDto);
        return "admin/user_change";
    }

    //회원정보 수정하기
    @GetMapping("/update")
    public String updateAccount(@RequestParam("username") String username,
                                Model model){
        UserAccountDto accountDto = adminService.getUserPageNoPassword(username);

//        System.out.println(accountDto.toString());

        model.addAttribute("accountDto", accountDto);
        return "/admin/user_updateForm";
    }



    @PostMapping("/update")
    public String update(@ModelAttribute("accountDto") UserAccountDto dto,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/user_updateForm";
        }

        adminService.updateUser(dto);// 사용자 정보 업데이트 메서드를 호출해야 함

        return "redirect:/admin/user_all";
    }



    //회원 강제 탈퇴
       @PostMapping("/userDelete/{deleteId}")
       public String deleteUser(@PathVariable("deleteId") String username,
                                   RedirectAttributes redirectAttributes){

           userAccountService.deleteAccount(username);
           redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 정상적으로 처리되었습니다!!");
           return "redirect:/admin/user_all";
       }



    //상품 삭제하기
       @PostMapping("/delete/{deleteId}")
       public String deleteProduct(@PathVariable("deleteId") Long productId,
                                   RedirectAttributes redirectAttributes){


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



}
