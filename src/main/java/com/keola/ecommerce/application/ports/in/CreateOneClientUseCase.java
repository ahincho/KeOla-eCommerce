package com.keola.ecommerce.application.ports.in;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Mono;

public interface CreateOneClientUseCase {
    Mono<Client> execute(Client client);
}
