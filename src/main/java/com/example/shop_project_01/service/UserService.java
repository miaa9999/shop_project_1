package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
       public void createUser(UserAccountDto userCreateForm) {
              UserAccount account = new UserAccount();
              account.setUserId(userCreateForm.getUserId());
              account.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
              account.setUserEmail(userCreateForm.getUserEmail());
              account.setUserName(userCreateForm.getUserName());
              account.setUserPhone(userCreateForm.getUserPhone());
              if ("ADMIN".equals(userCreateForm.getUserId().toUpperCase())){
                     account.setUserRole(UserRole.ADMIN);
              }else {
                     account.setUserRole(UserRole.USER);
              }
              em.persist(account);
       }

}
