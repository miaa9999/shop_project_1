package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
    UserAccount findByUsername(String username);
//       Optional<UserAccount> findByProviderAndProviderId(String provider, String providerId);

}

