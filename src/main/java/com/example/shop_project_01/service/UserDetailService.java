package com.example.shop_project_01.service;

import com.example.shop_project_01.config.PrincipalDetails;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.UserAccountRepository;
import com.example.shop_project_01.config.PrincipalDetails;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public UserDetailService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = userAccountRepository.findById(username);
        if(account.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        UserAccount userAccount = account.get();
        return new PrincipalDetails(userAccount);
    }
}
