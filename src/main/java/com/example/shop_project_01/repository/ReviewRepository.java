package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
