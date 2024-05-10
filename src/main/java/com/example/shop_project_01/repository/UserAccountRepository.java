package com.example.shop_project_01.repository;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
    UserAccount findByUsername(String username);

    @Query("SELECT u FROM UserAccount u WHERE u.username = :username")
    UserAccount findByUsernameWithoutPassword(@Param("username") String username);

    List<UserAccount> findByUserRoleNot(UserRole userRole);
//       Optional<UserAccount> findByProviderAndProviderId(String provider, String providerId);

}

