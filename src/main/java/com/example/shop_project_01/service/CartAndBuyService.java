package com.example.shop_project_01.service;

// <<<<<<< sh2
// import com.example.shop_project_01.entity.CartProduct;
// import com.example.shop_project_01.entity.UserAccount;
// import com.example.shop_project_01.repository.CartRepository;
// import com.example.shop_project_01.repository.UserAccountRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Collections;
// =======
import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.CartRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// >>>>>>> 서현
import java.util.List;

@Service
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

// <<<<<<< sh2
//     private final UserAccountRepository userAccountRepository;

//     public CartAndBuyService(UserAccountRepository userAccountRepository) {
//         this.userAccountRepository = userAccountRepository;
//     }


//     public List<CartProduct> showMyCart(String username) {
//         // 사용자의 username으로 해당 사용자 정보를 조회합니다.
//         UserAccount userAccount = userAccountRepository.findByUsername(username);

//         // 사용자가 존재하고 장바구니가 비어있지 않다면 장바구니에 담긴 상품 목록을 반환합니다.
//         if (userAccount != null && userAccount.getCart() != null) {
//             return userAccount.getCart().getCartProducts();
//         } else {
//             // 사용자가 존재하지 않거나 장바구니가 비어있는 경우 빈 리스트를 반환합니다.
//             return Collections.emptyList();
//         }
//     }

//     }

// =======
    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    EntityManager em;

    public Long cartIdFindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    //장바구니에 담기
    public void addCartProduct(CartProductDto dto) {
        System.out.println("==============="+dto);
        CartProduct cartProduct = new CartProduct();
      
        Cart cart = em.find(Cart.class, dto.getCartId());
        Product product = em.find(Product.class, dto.getProductId());
        
        cartProduct.setProductPrice(dto.getProductPrice());
        cartProduct.setCount(dto.getCount());
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
//        System.out.println(cart);
//        em.find(Product.class,cartProductDto.getProductId());
        cartProductRepository.save(cartProduct);
    }
    
    
}
// >>>>>>> 서현
