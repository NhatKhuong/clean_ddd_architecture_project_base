package com.example.infrastructure.persistences.repository;

import com.example.infrastructure.persistences.entity.ProductJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductSpringDataRepository extends JpaRepository<ProductJPAEntity, Long> {
    Optional<ProductJPAEntity> findByCode(String code);
}
