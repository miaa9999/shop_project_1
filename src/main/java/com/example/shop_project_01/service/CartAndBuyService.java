package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.BuyDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.*;
import com.example.shop_project_01.repository.CartProductRepository;
import com.example.shop_project_01.repository.CartRepository;
import com.example.shop_project_01.repository.ProductRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    CartProductRepository cartProductRepository;
    
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EntityManager em;

    //유저아이디로 유저의 카트아이디 가져오기
    public Long cartIdFindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    //장바구니에 담기
    public void addCartProduct(CartProductDto dto) {

        Cart cart = em.find(Cart.class, dto.getCartId());
        Product product = em.find(Product.class, dto.getProductId());
        List<CartProduct> cartProductList = cartProductRepository.findByCart_CartId(dto.getCartId());
//        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        int ifAddCount = dto.getCount();
        boolean productChange = false;

        //카트에 중복된 물건이 있을경우 수량을 더하여 카트에 다시 저장
        for (CartProduct cartProduct : cartProductList) {
            if (cartProduct.getProduct().getProductId().equals(dto.getProductId())) {
                ifAddCount += cartProduct.getCount();
                cartProduct.setCount(ifAddCount);
                cartProductRepository.save(cartProduct);
                productChange = true;
                break;

            }
        }
        //카트에 중복된 물건이 없을경우 카트에 새로 저장
            if (!productChange) {
                CartProduct cartProduct = new CartProduct();
                ifAddCount = dto.getCount();
                cartProduct.setCount(ifAddCount);
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);

                cartProductRepository.save(cartProduct);
            }
        }

    //유저의 전체 장바구니 가져오기
    public List<CartProduct> showMyCart(String username) {
        Cart cart = cartRepository.findByUserAccount_Username(username);
        List<CartProduct> cartProducts = cartProductRepository.findByCart_CartId(cart.getCartId());

        return cartProducts;
    }
       
       
       public String productNameFindByProductId(Long productId) {
        String productName = productRepository.findById(productId).map(x->ProductDto.fromProductEntity(x)).get().getProductName();

        return productName;
       }
    
    public int userPointFindByUsername(String loginUsername) {
        int userPoint = userAccountRepository.findByUsername(loginUsername).getInsertPoint();
        return userPoint;
    }
    
    public void addBuyProductOne(BuyDto buyDto) {
        UserAccount userAccount = em.find(UserAccount.class,buyDto.getUsername());
        Product product = em.find(Product.class,buyDto.getProductId());
        BuyProduct buyProduct = new BuyProduct();
        
        int totalPrice =  buyDto.getPrice()*buyDto.getCount();
        buyProduct.setCount(buyDto.getCount());
        buyProduct.setPrice(buyDto.getPrice());
        buyProduct.setProduct(product);
        buyProduct.setTotalPrice(totalPrice);
        Buy buy = new Buy();
        buy.setBuyDate(buyDto.getBuyDate());
        buy.setProductStatus(buyDto.getProductStatus());
        buy.setUserAccount(userAccount);
        
        buyProduct.setBuy(buy);
        buy.getBuyProducts().add(buyProduct);
        
        em.persist(buy);
        em.persist(buyProduct);
        
        userAccount.setInsertPoint(userAccount.getInsertPoint()-totalPrice);
        em.persist(userAccount);
    }
}
