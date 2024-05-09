package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDto getMyPage(String username) {
        UserAccount userAccount = userAccountRepository.findById(username).orElse(null);
        if(userAccount == null){
            return null;
        }else {
            return userAccountRepository.findById(username)
                    .map(x -> UserAccountDto.fromUserAccountEntity(x))
                    .orElse(null);
        }
    }
    public void deleteAccount(String username) {
        userAccountRepository.deleteById(username);
    }


}

