package com.keola.ecommerce.application.ports.in.clients;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Mono;

public interface CreateOneClientUseCase {
    Mono<Client> execute(Client client);
}
