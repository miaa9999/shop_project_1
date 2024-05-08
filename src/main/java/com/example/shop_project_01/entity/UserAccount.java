package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class UserAccount {
    @Id
    @Column(name = "user_id",length = 50)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_name",length = 10)
    private String userName;
    @Column(name = "user_phone",length = 30)
    private String userPhone;
    @Column(name = "user_email",length = 50)
    private String userEmail;
    private UserRole userRole;
//    @Column(name = "created_at",updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @Column(name = "updated_at",insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

}
