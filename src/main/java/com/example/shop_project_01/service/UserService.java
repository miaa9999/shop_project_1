package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
       
       private final UserAccountRepository userAccountRepository;
       
       public UserService(UserAccountRepository userAccountRepository) {
              this.userAccountRepository = userAccountRepository;
       }
       @Autowired
       PasswordEncoder passwordEncoder;
       
       @Autowired
       EntityManager em;
       
       @Transactional
       public void createUser(UserAccountDto dto) {
              UserAccount account = new UserAccount();
              account.setUsername(dto.getUsername());
              account.setPassword(passwordEncoder.encode(dto.getPassword()));
              account.setUserEmail(dto.getUserEmail());
              account.setName(dto.getName());
              account.setUserPhone(dto.getUserPhone());
              if ("ADMIN".equals(dto.getUsername().toUpperCase())){
                     account.setUserRole(UserRole.ADMIN);
              }else {
                     account.setUserRole(UserRole.USER);
              }
              em.persist(account);
       }
}
