package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//유저당 배정된 장바구니
public class Cart {
    //장바구니 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;



    //배정된 유저아이디
    @OneToOne
    @JoinColumn(name = "username")
    private UserAccount userAccount;

}
