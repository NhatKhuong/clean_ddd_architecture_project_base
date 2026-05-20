package com.example.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

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
