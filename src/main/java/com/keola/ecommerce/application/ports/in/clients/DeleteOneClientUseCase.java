package com.keola.ecommerce.application.ports.in.clients;

import reactor.core.publisher.Mono;

public interface DeleteOneClientUseCase {
    Mono<Void> execute(Integer clientId);
}
