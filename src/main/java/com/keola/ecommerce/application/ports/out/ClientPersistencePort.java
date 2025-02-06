package com.keola.ecommerce.application.ports.out;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientPersistencePort {
    Mono<Client> createOneClient(Client client);
    Flux<Client> findClients();
    Mono<Client> findOneClient(Integer clientId);
    Mono<Client> findOneClientByEmail(String email);
    Mono<Boolean> existsOneClientById(Integer clientId);
    Mono<Boolean> existsOneClientByEmail(String email);
    Mono<Void> updateOneClient(Integer clientId, Client client);
    Mono<Void> deleteOneClient(Integer clientId);
}
