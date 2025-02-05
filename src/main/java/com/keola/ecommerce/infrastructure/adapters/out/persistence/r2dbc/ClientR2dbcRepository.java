package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface ClientR2dbcRepository extends R2dbcRepository<ClientEntity, String> {
    Mono<Boolean> existsByEmail(String email);
}
