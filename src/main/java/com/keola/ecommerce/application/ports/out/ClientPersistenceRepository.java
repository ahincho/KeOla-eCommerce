package com.keola.ecommerce.application.ports.out;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientPersistenceRepository {
    Mono<Client> createOneClient(Client client);
    Flux<Client> findClients();
    Mono<Client> findOneClient(String clientId);
    Mono<Boolean> existsOneClientByEmail(String email);
    Mono<Void> updateOneClient(String clientId, Client client);
    Mono<Void> deleteOneClient(String clientId);
}
