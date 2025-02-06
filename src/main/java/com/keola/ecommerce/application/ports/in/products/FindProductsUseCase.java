package com.keola.ecommerce.application.ports.in.products;

import com.keola.ecommerce.domain.models.Product;

import reactor.core.publisher.Flux;

public interface FindProductsUseCase {
    Flux<Product> execute();
}
