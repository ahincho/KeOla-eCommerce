package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.products;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface ProductR2dbcRepository extends R2dbcRepository<ProductEntity, Integer> {
    Mono<ProductEntity> findByName(String name);
    Mono<Boolean> existsByName(String name);
}
