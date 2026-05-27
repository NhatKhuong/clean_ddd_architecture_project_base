package com.example.application.command.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommand {
    private String code;
    private String name;
    private Double price;
    private Long categoryId;
}
