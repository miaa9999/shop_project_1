package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
       @Autowired
       ProductRepository productRepository;
       public List<ProductDto> productViewAll() {
              List<ProductDto> products = productRepository.findAll().stream().map(x-> ProductDto.fromProductEntity(x)).toList();
              return products;
       }

       public ProductDto productViewOne(Long productId) {
              Product product = productRepository.findById(productId).orElse(null);
              if (product != null) {
                     return ProductDto.fromProductEntity(product);
              } else {
                     return null;
              }
       }


}
