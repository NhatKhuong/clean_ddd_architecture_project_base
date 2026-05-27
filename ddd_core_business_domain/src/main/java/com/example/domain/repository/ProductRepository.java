package com.example.domain.repository;

import com.example.domain.aggregate.Product;

import java.util.List;
import java.util.Optional;
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    boolean existsByCode(String code);
}
