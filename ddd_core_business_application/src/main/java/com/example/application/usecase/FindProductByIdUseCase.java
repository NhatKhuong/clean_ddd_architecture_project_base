package com.example.application.usecase;

import com.example.application.dto.ProductResultDto;

public interface FindProductByIdUseCase {
    ProductResultDto execute (Long id);
}
