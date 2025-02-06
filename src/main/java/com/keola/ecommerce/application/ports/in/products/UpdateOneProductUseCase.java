package com.keola.ecommerce.application.ports.in.products;

import com.keola.ecommerce.domain.models.Product;

import reactor.core.publisher.Mono;

public interface UpdateOneProductUseCase {
    Mono<Void> execute(Integer productId, Product product);
}
