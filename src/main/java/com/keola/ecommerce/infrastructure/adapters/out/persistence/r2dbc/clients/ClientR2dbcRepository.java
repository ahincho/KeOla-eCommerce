package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.clients;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface ClientR2dbcRepository extends ReactiveCrudRepository<ClientEntity, Integer> {
    Mono<ClientEntity> findByEmail(String email);
    Mono<Boolean> existsByEmail(String email);
}
