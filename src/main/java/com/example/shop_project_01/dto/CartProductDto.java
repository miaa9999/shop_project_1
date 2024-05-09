package com.example.shop_project_01.dto;

import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {

    //장바구니 아이템 번호
    private Long cartProductId;

    // 구매수량
    private int count;

    //상품 번호
    private Long productId;

    //구매당시의 상품 가격 ( 조인x _ 할인할때 가격을 알기위함 )
    private int productPrice;

    //배정된 카트 아이디
    private Cart cart;

    public CartProductDto(int count, Long productId, int productPrice, Cart cart) {
        this.count = count;
        this.productId = productId;
        this.productPrice = productPrice;
        this.cart = cart;
    }

    public CartProductDto(int count, Long productId, int productPrice, Long cartId) {
        this.count = count;
        this.productId = productId;
        this.productPrice = productPrice;
        cart.getCartId();
    }

    public static CartProduct fromCartProductDto(CartProductDto dto){
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartProductId(dto.getCartProductId());
        cartProduct.setCount(dto.getCount());
        cartProduct.setProductId(dto.getProductId());
        cartProduct.setProductPrice(dto.getProductPrice());
        cartProduct.getCart().setCartId(dto.getCart().getCartId());
        return cartProduct;
    }

}
