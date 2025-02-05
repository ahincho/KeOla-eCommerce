package com.keola.ecommerce.application.ports.in;

import com.keola.ecommerce.domain.models.Client;

import reactor.core.publisher.Flux;

public interface FindClientsUseCase {
    Flux<Client> execute();
}
