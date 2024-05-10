package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.ProductRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {
       
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    EntityManager em;
       
       public List<ProductDto> productViewAll() {
              return productRepository.findAll().stream().map(x->ProductDto.fromProductEntity(x)).toList();
       }

       public ProductDto productViewFindById(Long productId) {
           return productRepository.findById(productId).map(x -> ProductDto.fromProductEntity(x)).orElse(null);
       }
       public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        }

    public List<UserAccountDto> showAllUserExceptAdmin() {
        List<UserAccount> users = userAccountRepository.findByUserRoleNot(UserRole.ADMIN);
        return users.stream().map(UserAccountDto::fromUserAccountEntity).collect(Collectors.toList());
    }

    public void updateUser(@Valid UserAccountDto dto) {
           UserAccount userAccount = em.find(UserAccount.class,dto.getUsername());
           userAccount.setUserEmail(dto.getUserEmail());
           userAccount.setUserAddress(dto.getUserAddress());
           userAccount.setUserPhone(dto.getUserPhone());
           userAccount.setName(dto.getName());

           em.persist(userAccount);

//        UserAccount changeUserAccount = UserAccountDto.fromUserAccountDto(dto);
//        UserAccount originUserAccount = userAccountRepository.findByUsername(dto.getUsername());

//        originUserAccount.setUserAddress(changeUserAccount.getUserAddress());
//        originUserAccount.setUserPhone(changeUserAccount.getUserPhone());
//        originUserAccount.setName(changeUserAccount.getName());
//        originUserAccount.setUserEmail(changeUserAccount.getUserEmail());
//        originUserAccount.setPassword(originUserAccount.getPassword());



//        userAccountRepository.save(originUserAccount);

    }
    public UserAccountDto getUserPage(String username) {
        UserAccount userAccount = userAccountRepository.findById(username).orElse(null);
        if(userAccount == null){
            return null;
        }else {
            return userAccountRepository.findById(username)
                    .map(x -> UserAccountDto.fromUserAccountEntityNoPassword(x))
                    .orElse(null);
        }
    }

    public UserAccountDto getUserPageNoPassword(String username) {
        UserAccountDto userAccountDto = userAccountRepository.
                findById(username).map(x->UserAccountDto.fromUserAccountEntityNoPassword(x)).orElse(null);
        return userAccountDto;
       }



}
