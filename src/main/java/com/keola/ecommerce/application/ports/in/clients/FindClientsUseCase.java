package com.keola.ecommerce.application.ports.in.clients;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Flux;

public interface FindClientsUseCase {
    Flux<Client> execute();
}
