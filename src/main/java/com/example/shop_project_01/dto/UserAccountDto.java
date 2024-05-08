package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class UserAccountDto {
    private String userId;
    private String password;
    private String passwordCheck;
    private String userName;
    private String userPhone;
    private String userEmail;
    private UserRole userRole;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    
    public UserAccountDto(String userId, String password, String userName, String userPhone, String userEmail, UserRole userRole) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userRole = userRole;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public static UserAccountDto fromUserAccountEntity(UserAccount userAccount){
        return new UserAccountDto(
                userAccount.getUserId(),
                userAccount.getPassword(),
                userAccount.getUserName(),
                userAccount.getUserPhone(),
                userAccount.getUserEmail(),
                userAccount.getUserRole()
//                userAccount.getCreatedAt()
//                userAccount.getUpdatedAt()
        );
    }

    public UserAccount fromUserAccountDto(UserAccountDto dto){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(dto.getUserId());
        userAccount.setPassword(dto.getPassword());
        userAccount.setUserName(dto.getUserName());
        userAccount.setUserPhone(dto.getUserPhone());
        userAccount.setUserEmail(dto.getUserEmail());
        userAccount.setUserRole(dto.getUserRole());
//        userAccount.setCreatedAt(dto.getCreatedAt());
//        userAccount.setUpdatedAt(dto.getUpdatedAt());
        return userAccount;
    }



}
