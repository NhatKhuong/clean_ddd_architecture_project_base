package com.example.application.service;

import com.example.application.dto.ProductResultDto;
import com.example.application.usecase.FindProductByIdUseCase;
import com.example.domain.aggregate.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
@Service
public class FindProductByIdService implements FindProductByIdUseCase {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResultDto execute(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductResultDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategoryId()).build();
    }
}
