package com.example.application.service.product.impl;

import com.example.application.dto.product.ProductResultDto;
import com.example.application.redis.RedisApplicationService;
import com.example.application.redission.RedisDistributedService;
import com.example.application.service.product.FindProductByIdUseCase;
import com.example.domain.aggregate.Product;
import com.example.domain.repository.ProductRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.BeanUtil;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class FindProductByIdUseCaseHandler implements FindProductByIdUseCase {

    private final ProductRepository productRepository;
    private final RedisDistributedService redisDistributedService;
    private final RedisApplicationService redisApplicationService;

    private static final Cache<Long, Product> ticketDetailLocalCache =
            Caffeine.newBuilder()
                    .initialCapacity(10)
                    .maximumSize(10_000)
                    .expireAfterWrite(100, TimeUnit.MINUTES)
                    .build();

    public FindProductByIdUseCaseHandler(ProductRepository productRepository, RedisDistributedService redisDistributedService, RedisApplicationService redisApplicationService) {
        this.productRepository = productRepository;
        this.redisDistributedService = redisDistributedService;
        this.redisApplicationService = redisApplicationService;
    }
    private String genEventItemKey(Long itemId) {
        return "PRO:ITEM:" + itemId;
    }
    @Override
    public ProductResultDto execute(Long id) {
        // 1. get ticket item by redis
        Product product = redisApplicationService.getObject(genEventItemKey(id), Product.class);
        // 2. YES -> Hit cache
        if (product != null) {
            log.info("HIT CACHE ======================");
            return ProductResultDto.builder()
                    .id(product.getId())
                    .code(product.getCode())
                    .name(product.getName())
                    .price(product.getPrice())
                    .categoryId(product.getCategoryId()).build();
        }
        log.info("GET DATABASE ======================");
        product = productRepository.findById(id).orElse(null);
        if (product != null) {
            redisApplicationService.setObject(genEventItemKey(id), product);
        }

        return ProductResultDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategoryId()).build();
    }
}
