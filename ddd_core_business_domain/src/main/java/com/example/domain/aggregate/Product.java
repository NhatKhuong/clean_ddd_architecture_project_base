package com.example.domain.aggregate;

import com.example.domain.valueobject.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean active;
    private Double price;
    private String currency;
    private Long quantity;
    private Long categoryId;
    private final List<ProductImage> images = new ArrayList<>();

}
