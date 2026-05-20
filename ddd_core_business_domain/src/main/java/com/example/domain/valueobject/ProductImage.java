package com.example.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private Long id;
    private String url;
    private String thumbnailUrl;
    private String altText;
    private Boolean main;
    private Integer sortOrder;
}
