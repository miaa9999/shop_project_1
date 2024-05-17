package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.ReviewDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.Review;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    EntityManager em;
    @Autowired
    UserService userService;
    public void addReview(ReviewDto dto) {
        String username = userService.loginUsername();
        UserAccount userAccount = em.find(UserAccount.class,username);
        Product product = em.find(Product.class,dto.getProductId());

        // ReviewDto에서 필요한 정보 추출
        String content = dto.getContent();
//        Long productId = dto.getProductId(); // 리뷰가 속하는 제품 ID 등

        // 새로운 리뷰 객체 생성
        Review review = new Review();
        review.setContent(content);
        review.setProduct(product);
        review.setUserAccount(userAccount);
//        review.getProduct().setProductId(productId);
        // 제품 ID나 다른 필요한 정보 설정

        // 리뷰 저장
        reviewRepository.save(review);
    }


}
