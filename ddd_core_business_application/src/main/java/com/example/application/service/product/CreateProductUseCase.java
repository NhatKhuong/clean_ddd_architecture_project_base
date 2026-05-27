package com.example.application.service.product;

import com.example.application.command.product.CreateProductCommand;
import com.example.application.dto.product.ProductResultDto;

public interface CreateProductUseCase {
    ProductResultDto execute(CreateProductCommand command);
}
