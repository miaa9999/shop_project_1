package com.example.shop_project_01.dto;

import com.example.shop_project_01.entity.Product;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ProductDto {
    private Long productId;
    private String productName;
    private int productPrice;
    private int productStock;
    private String content;
    private String mainCategory;
    private String subCategory;

    public static ProductDto fromProductEntity(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock(),
                product.getContent(),
                product.getMainCategory(),
                product.getSubCategory()
        );
    }

    public Product fromProductDto(ProductDto dto){
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductStock(dto.getProductStock());
        product.setContent(dto.getContent());
        product.setMainCategory(dto.getMainCategory());
        product.setSubCategory(dto.getSubCategory());
        return product;
    }
}
