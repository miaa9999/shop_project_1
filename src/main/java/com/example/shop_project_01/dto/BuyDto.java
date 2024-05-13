package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto {
       public BuyDto(LocalDateTime buyDate, String username, ProductStatus productStatus) {
              this.buyDate = buyDate;
              this.username = username;
              this.productStatus = productStatus;
       }
       
       public BuyDto(LocalDateTime buyDate, ProductStatus productStatus) {
              this.buyDate = buyDate;
              this.productStatus = productStatus;
       }
       
       //구매확정
       private Long buyId;
       //구매 날짜
       private LocalDateTime buyDate;
       //구매한 유저 아이디 ( joinColumn - UserAccount )
       private String username;
       //유저가 충전한 금액
//       private int insertPoint;
       
       //구매상태
       private ProductStatus productStatus;
       
       //구매내역 번호 (상세)
       private Long buyProductId;
       
//       //구매내역 번호 (상세) - 구매수량
//       private int count;
//
//       //구매내역 번호 (상세) - 구매가격 ( join 아님 )
//       private int price;
//
//       //구매내역 번호 (상세) - 상품번호
//       private Long productId;
//
//       private String productName;

//       public BuyDto(String username, int count, int price, String productName, Long productId,int insertPoint) {
//              this.username = username;
//              this.count = count;
//              this.price = price;
//              this.productName = productName;
//              this.productId = productId;
//              this.insertPoint = insertPoint;
//       }
//
//       public BuyDto(LocalDateTime buyDate, String username, ProductStatus productStatus, int count, int price, Long productId) {
//              this.buyDate = buyDate;
//              this.username = username;
//              this.productStatus = productStatus;
//              this.count = count;
//              this.price = price;
//              this.productId = productId;
//       }
}
