package com.keola.ecommerce.application.ports.in.orders;

import reactor.core.publisher.Mono;

public interface DeleteOneOrderUseCase {
    Mono<Void> execute(String orderId);
}
