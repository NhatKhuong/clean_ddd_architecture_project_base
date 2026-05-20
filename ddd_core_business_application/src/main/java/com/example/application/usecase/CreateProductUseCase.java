package com.example.application.usecase;

import com.example.application.command.CreateProductCommand;
import com.example.application.dto.ProductResultDto;

public interface CreateProductUseCase {
    ProductResultDto execute(CreateProductCommand command);
}
