package com.example.infrastructure.persistences.repository.impl;

import com.example.domain.aggregate.Product;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistences.entity.ProductJPAEntity;
import com.example.infrastructure.persistences.repository.ProductSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private ProductSpringDataRepository productSpringDataRepository;
    @Override
    public Product save(Product product) {
        ProductJPAEntity entity = toEntity(product);
        ProductJPAEntity savedEntity = productSpringDataRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productSpringDataRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productSpringDataRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    private ProductJPAEntity toEntity(Product product) {
        ProductJPAEntity entity = new ProductJPAEntity();
        entity.setId(product.getId());
        entity.setCode(product.getCode());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
//        entity.setCategoryId(product.getCategoryId());
        return entity;
    }

    private Product toDomain(ProductJPAEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .price(entity.getPrice())
//                .categoryId(entity.getCategoryId())
                .build();
    }
}
