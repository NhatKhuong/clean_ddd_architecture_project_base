package com.example.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResultDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean active;
    private Double price;
    private String currency;
    private Long quantity;
    private Long categoryId;
}
