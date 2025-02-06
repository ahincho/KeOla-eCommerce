package com.keola.ecommerce.application.ports.in.products;

import reactor.core.publisher.Mono;

public interface DeleteOneProductUseCase {
    Mono<Void> execute(Integer productId);
}
