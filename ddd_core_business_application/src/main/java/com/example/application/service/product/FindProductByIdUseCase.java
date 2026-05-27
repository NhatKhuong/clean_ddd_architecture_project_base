package com.example.application.service.product;

import com.example.application.dto.product.ProductResultDto;

public interface FindProductByIdUseCase {
    ProductResultDto execute (Long id);
}
