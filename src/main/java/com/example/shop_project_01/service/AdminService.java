package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.*;
import com.example.shop_project_01.entity.BuyProduct;
import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
       
       @Autowired
       BuyRepository buyRepository;
       
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
       
//       public List<NoticeDto> showAllNotice() {
//              return noticeRepository.findAll().stream().map(x -> NoticeDto.fromNoticeEntity(x)).toList();
//       }
       
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
       
//       public List<BuyDto> showSalesAllForBuyDto() {
//              List<BuyDto> dtos = buyRepository.findAll().stream().map(x -> BuyDto.buyDtoFromEntity(x)).toList();
//              List<BuyDto> dtoList = new ArrayList<>();
//              String statues = null;
//              int total = 0;
//
//              for (BuyDto buyDto : dtos) {
//                     buyDto.setDate(buyDto.getBuyDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH : mm")));
//                     switch (buyDto.getProductStatus()) {
//                            case FINISH:
//                                   statues = "배송완료";
//                                   buyDto.setStatues(statues);
//                                   break;
//
//                            case DELIVER:
//                                   statues = "배송중";
//                                   buyDto.setStatues(statues);
//                                   break;
//
//                            case DEPOSIT:
//                                   statues = "입금완료";
//                                   buyDto.setStatues(statues);
//                                   break;
//                     }
//              }
//              return dtos;
//       }
//
       public List<BuyProductDto> showSalesAllDeliver() {
              List<BuyProductDto> dtos = buyProductRepository.findByBuy_ProductStatus(ProductStatus.DELIVER).stream().map(x->BuyProductDto.buyProductDtoFromEntity(x)).toList();
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
       
       public List<BuyProductDto> showSalesAllDeposit() {
              List<BuyProductDto> dtos = buyProductRepository.findByBuy_ProductStatus(ProductStatus.DEPOSIT).stream().map(x->BuyProductDto.buyProductDtoFromEntity(x)).toList();
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
       
       public List<BuyProductDto> showSalesAllFinish() {
              List<BuyProductDto> dtos = buyProductRepository.findByBuy_ProductStatus(ProductStatus.FINISH).stream().map(x->BuyProductDto.buyProductDtoFromEntity(x)).toList();
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
//<<<<<<< HEAD
//
//=======
//페이징 처리 목록들
    public Page<Notice> pagingListNotice(Pageable pageable) {
           return noticeRepository.findAll(pageable);
    }

       public Page<Product> pagingListProduct(Pageable pageable) {
              return productRepository.findAll(pageable);
       }

       public Page<UserAccount> pagingListUser(Pageable pageable) {
              return userAccountRepository.findAllExceptAdmin(pageable);
       }

       public Page<BuyProductDto> pagingListBuyProduct(Pageable pageable) {
              Page<BuyProduct> page = buyProductRepository.findAll(pageable);
              List<BuyProductDto> dtos = page.getContent().stream()
                      .map(x -> {
                             BuyProductDto dto = BuyProductDto.buyProductDtoFromEntity(x);
                             String productName = cartAndBuyService.productNameFindByProductId(dto.getProductId());
                             dto.setProductName(productName);
                             dto.setDate(dto.getSalesDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm")));
                             switch (dto.getProductStatus()) {
                                    case FINISH:
                                           dto.setStatues("배송완료");
                                           break;
                                    case DELIVER:
                                           dto.setStatues("배송중");
                                           break;
                                    case DEPOSIT:
                                           dto.setStatues("입금완료");
                                           break;
                             }
                             return dto;
                      })
                      .collect(Collectors.toList());
              return new PageImpl<>(dtos, pageable, page.getTotalElements());
       }

}
