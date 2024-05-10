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
       //구매확정
       private Long buyId;
       //구매 날짜
       private LocalDateTime buyDate;
       //구매한 유저 아이디 ( joinColumn - UserAccount )
       private String username;
       
       //구매상태
       private ProductStatus productStatus;
       
       //구매내역 번호 (상세)
       private Long buyProductId;
       
       //구매내역 번호 (상세) - 구매수량
       private int count;
       
       //구매내역 번호 (상세) - 구매가격 ( join 아님 )
       private int price;
       
       //구매내역 번호 (상세) - 상품번호
       private Long productId;
       
       private String productName;
       
       public BuyDto(String username, int count, int price, String productName, Long productId) {
              this.username = username;
              this.count = count;
              this.price = price;
              this.productName = productName;
              this.productId = productId;
       }
       
}
