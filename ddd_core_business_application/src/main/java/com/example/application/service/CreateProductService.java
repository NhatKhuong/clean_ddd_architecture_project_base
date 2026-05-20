package com.example.application.service;

import com.example.application.command.CreateProductCommand;
import com.example.application.dto.ProductResultDto;
import com.example.application.usecase.CreateProductUseCase;
import com.example.domain.aggregate.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateProductService implements CreateProductUseCase {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResultDto execute(CreateProductCommand command) {
        Product product = Product.builder()
                .code(command.getCode())
                .name(command.getName())
                .price(command.getPrice())
                .categoryId(command.getCategoryId())
                .build();

        Product saved = productRepository.save(product);

        return ProductResultDto.builder()
                .id(saved.getId())
                .code(saved.getCode())
                .name(saved.getName())
                .price(saved.getPrice())
                .categoryId(saved.getCategoryId())
                .build();
    }
}
