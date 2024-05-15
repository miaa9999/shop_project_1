package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.BuyProductDto;
import com.example.shop_project_01.dto.NoticeDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.BuyProduct;
import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.BuyProductRepository;
import com.example.shop_project_01.repository.NoticeRepository;
import com.example.shop_project_01.repository.ProductRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {
       
       @Autowired
       ProductRepository productRepository;
       @Autowired
       UserAccountRepository userAccountRepository;
       @Autowired
       NoticeRepository noticeRepository;
       
       @Autowired
       BuyProductRepository buyProductRepository;
       @Autowired
       EntityManager em;
       
       @Autowired
       CartAndBuyService cartAndBuyService;
       
       public List<ProductDto> productViewAll() {
              return productRepository.findAll().stream().map(x -> ProductDto.fromProductEntity(x)).toList();
       }
       
       public ProductDto productViewFindById(Long productId) {
              return productRepository.findById(productId).map(x -> ProductDto.fromProductEntity(x)).orElse(null);
       }
       
       public void deleteProduct(Long productId) {
              productRepository.deleteById(productId);
       }
       
       public List<UserAccountDto> showAllUserExceptAdmin() {
              List<UserAccount> users = userAccountRepository.findByUserRoleNot(UserRole.ADMIN);
              return users.stream().map(UserAccountDto::fromUserAccountEntity).collect(Collectors.toList());
       }
       
       public void updateUser(UserAccountDto dto) {
              UserAccount userAccount = em.find(UserAccount.class, dto.getUsername());
              userAccount.setUserEmail(dto.getUserEmail());
              userAccount.setUserAddress(dto.getUserAddress());
              userAccount.setUserPhone(dto.getUserPhone());
              userAccount.setName(dto.getName());
              
              em.persist(userAccount);
       }
       
       public UserAccountDto getUserPage(String username) {
              UserAccount userAccount = userAccountRepository.findById(username).orElse(null);
              if (userAccount == null) {
                     return null;
              } else {
                     return userAccountRepository.findById(username)
                            .map(x -> UserAccountDto.fromUserAccountEntityNoPassword(x))
                            .orElse(null);
              }
       }
       
       public UserAccountDto getUserPageNoPassword(String username) {
              UserAccountDto userAccountDto = userAccountRepository.
                     findById(username).map(x -> UserAccountDto.fromUserAccountEntityNoPassword(x)).orElse(null);
              return userAccountDto;
       }
       
       public void addProduct(ProductDto dto) {
              Product product = dto.fromProductDto(dto);
              productRepository.save(product);
       }
       
       
       public void updateProduct(ProductDto dto) {
              Product product = dto.fromProductDto(dto);
              productRepository.save(product);
       }
       
       
       public void addNotice(NoticeDto dto) {
              Notice notice = dto.fromNoticeDto(dto);
              noticeRepository.save(notice);
       }
       
       public List<NoticeDto> showAllNotice() {
              return noticeRepository.findAll().stream().map(x -> NoticeDto.fromNoticeEntity(x)).toList();
       }
       
       public NoticeDto noticeViewFindById(Long noticeId) {
              return noticeRepository.findById(noticeId).map(x -> NoticeDto.fromNoticeEntity(x)).orElse(null);
       }
       
       public void deleteNotice(Long noticeId) {
              noticeRepository.deleteById(noticeId);
       }
       
       public void updateNotice(NoticeDto dto) {
              Notice notice = dto.fromNoticeDto(dto);
              noticeRepository.save(notice);
       }
       
       public List<BuyProductDto> showSalesAll() {
              List<BuyProductDto> dtos = buyProductRepository.findAll().stream().map(x -> BuyProductDto.buyProductDtoFromEntity(x)).toList();
              List<BuyProductDto> dtoList = new ArrayList<>();
              String statues = null;
              
              for (BuyProductDto buyDto : dtos) {
                     String productName = cartAndBuyService.productNameFindByProductId(buyDto.getProductId());
                     buyDto.setProductName(productName);
                     buyDto.setDate(buyDto.getSalesDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH : mm")));
                     switch (buyDto.getProductStatus()) {
                            case FINISH:
                                   statues = "배송완료";
                                   buyDto.setStatues(statues);
                                   break;
                            
                            case DELIVER:
                                   statues = "배송중";
                                   buyDto.setStatues(statues);
                                   break;
                            
                            case DEPOSIT:
                                   statues = "입금완료";
                                   buyDto.setStatues(statues);
                                   break;
                     }
              }
              return dtos;
       }
       
       public void updateProductStatus(Long buyProductId, String status) {
              BuyProduct buyProduct = em.find(BuyProduct.class,buyProductId);
              switch (status){
                     case "입금완료" :
                            buyProduct.getBuy().setProductStatus(ProductStatus.DEPOSIT);
                            break;
                     case "배송중" :
                            buyProduct.getBuy().setProductStatus(ProductStatus.DELIVER);
                            break;
                     case "배송완료" :
                            buyProduct.getBuy().setProductStatus(ProductStatus.FINISH);
                            break;
              }
              em.persist(buyProduct);
       }
}
