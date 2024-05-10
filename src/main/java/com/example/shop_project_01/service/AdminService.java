package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
       
       @Autowired
       ProductRepository productRepository;
       
       public List<ProductDto> productViewAll() {
              return productRepository.findAll().stream().map(x->ProductDto.fromProductEntity(x)).toList();
       }
       
       public ProductDto productViewFindById(Long productId) {
              return productRepository.findById(productId).map(x->ProductDto.fromProductEntity(x)).orElse(null);
       }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
