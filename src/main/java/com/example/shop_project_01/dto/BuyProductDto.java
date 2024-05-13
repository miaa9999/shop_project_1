package com.example.shop_project_01.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductDto {
       //구매내역 번호 (상세)
       private Long buyProductId;
       
       //구매내역 번호 (상세) - 구매수량
       private int count;
       
       //구매내역 번호 (상세) - 구매가격 ( join 아님 )
       private int price;
       
       //구매내역 번호 (상세) - 상품번호
       private Long productId;
       
       private String productName;
       
       //구매확정
       private Long buyId;
       
       private int totalPrice;
       public BuyProductDto(int count, int nowPrice, Long productId) {
       }
}
