package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//유저당 배정된 장바구니
public class Cart {
    //장바구니 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
// <<<<<<< sh2



//     //배정된 유저아이디 회원가입시 장바구니가 하나씩 자동생성(회원탈퇴 시 자동 삭제)
//     @OneToOne
//     @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
//     private UserAccount userAccount;


//     //카트에 담긴 상품 수
//     private int count;

//     //한 개의 카트에 상품이 여러개
//     @OneToMany(fetch = FetchType.EAGER)
//     private List<CartProduct> cartProducts = new ArrayList<>();

// =======
    
    //배정된 유저아이디
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private UserAccount userAccount;
    
    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts = new ArrayList<>();
// >>>>>>> 서현
}
