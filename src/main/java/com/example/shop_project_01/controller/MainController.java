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


       
       @GetMapping("/product_detail/{productId}")
       public String product_detail(@PathVariable("productId")Long productId, Model model) {
              ProductDto product = categoryService.productViewOne(productId);

              model.addAttribute("product",product);
              return "/product/product_detail";
       }
       
       @GetMapping("/doll")
       public String dollCategory(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> dollProducts = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getMainCategory().equals("인형")) {
                            dollProducts.add(product);
                     }
              }
              model.addAttribute("count",dollProducts.stream().count());
              model.addAttribute("dollAll", dollProducts);
              return "/product/doll_all";
       }
       
       @GetMapping("/doll_person")
       public String dollPerson(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> dollProducts = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("사람인형")) {
                            dollProducts.add(product);
                     }
              }
//              System.out.println("갯수 확인 : " + dollProducts.stream().count());
              model.addAttribute("count",dollProducts.stream().count());
              model.addAttribute("dollAll", dollProducts);
              return "/product/doll_person";
       }
       
       @GetMapping("/doll_animal")
       public String dollAnimal(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> dollProducts = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("동물인형")) {
                            dollProducts.add(product);
                     }
              }
              model.addAttribute("count",dollProducts.stream().count());
              model.addAttribute("dollAll", dollProducts);
              return "/product/doll_animal";
       }
       
       @GetMapping("/doll_character")
       public String dollCharacter(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> dollProducts = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("캐릭터인형")) {
                            dollProducts.add(product);
                     }
              }
              model.addAttribute("count",dollProducts.stream().count());
              model.addAttribute("dollAll", dollProducts);
              return "/product/doll_character";
       }

       @GetMapping("/toy")
       public String toyAll(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> toyProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getMainCategory().equals("완구")) {
                            toyProduct.add(product);
                     }
              }

              model.addAttribute("count",toyProduct.stream().count());
              model.addAttribute("toyAll", toyProduct);
              return "/product/toy_all";
       }

       @GetMapping("/toy_lego")
       public String toyLego(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> toyProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("레고")) {
                            toyProduct.add(product);
                     }
              }

              model.addAttribute("count",toyProduct.stream().count());
              model.addAttribute("toyAll", toyProduct);
              return "/product/toy_lego";
       }

       @GetMapping("/toy_outdoor")
       public String toyOutdoor(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> toyProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("실외장난감")) {
                            toyProduct.add(product);
                     }
              }

              model.addAttribute("count",toyProduct.stream().count());
              model.addAttribute("toyAll", toyProduct);
              return "/product/toy_outdoor";
       }

       @GetMapping("/toy_touch")
       public String toyTouch(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> toyProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("촉각놀이")) {
                            toyProduct.add(product);
                     }
              }

              model.addAttribute("count",toyProduct.stream().count());
              model.addAttribute("toyAll", toyProduct);
              return "/product/toy_touch";
       }

       @GetMapping("/books")
       public String bookAll(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> bookProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getMainCategory().equals("도서")) {
                            bookProduct.add(product);
                     }
              }

              model.addAttribute("count",bookProduct.stream().count());
              model.addAttribute("bookAll", bookProduct);
              return "/product/books_all";
       }

       @GetMapping("/books_story")
       public String bookStory(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> bookProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("동화")) {
                            bookProduct.add(product);
                     }
              }

              model.addAttribute("count",bookProduct.stream().count());
              model.addAttribute("bookAll", bookProduct);
              return "/product/books_story";
       }

       @GetMapping("/books_biography")
       public String booksBiography(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> bookProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("위인전")) {
                            bookProduct.add(product);
                     }
              }

              model.addAttribute("count",bookProduct.stream().count());
              model.addAttribute("bookAll", bookProduct);
              return "/product/books_biography";
       }

       @GetMapping("/books_eng")
       public String booksEng(Model model) {
              List<ProductDto> products = categoryService.productViewAll();
              List<ProductDto> bookProduct = new ArrayList<>();
              for (ProductDto product : products) {
                     if (product.getSubCategory().equals("영어도서")) {
                            bookProduct.add(product);
                     }
              }

              model.addAttribute("count",bookProduct.stream().count());
              model.addAttribute("bookAll", bookProduct);
              return "/product/books_eng";
       }


}

