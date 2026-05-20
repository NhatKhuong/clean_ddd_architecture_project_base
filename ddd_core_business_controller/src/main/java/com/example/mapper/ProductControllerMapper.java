package com.example.mapper;


import com.example.application.command.CreateProductCommand;
import com.example.application.dto.ProductResultDto;
import com.example.request.CreateProductRequest;
import com.example.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductControllerMapper {
    public CreateProductCommand toCreateCommand(CreateProductRequest request) {
        return new CreateProductCommand(
                request.getCode(),
                request.getName(),
                request.getPrice(),
                request.getCategoryId()
        );
    }

    public ProductResponse toResponse(ProductResultDto dto) {
        return ProductResponse.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .build();

    }
}
