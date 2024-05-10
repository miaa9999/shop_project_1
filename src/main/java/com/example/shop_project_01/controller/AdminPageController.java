package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
       @Autowired
       AdminService adminService;
       @Autowired
       CartProductRepository cartProductRepository;


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
