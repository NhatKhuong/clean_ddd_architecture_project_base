package com.example.application.service.product.impl;

import com.example.application.command.product.CreateProductCommand;
import com.example.application.dto.product.ProductResultDto;
import com.example.application.service.product.CreateProductUseCase;
import com.example.domain.aggregate.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCaseHandler implements CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCaseHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
