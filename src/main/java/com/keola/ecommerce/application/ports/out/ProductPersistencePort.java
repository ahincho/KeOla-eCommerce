package com.keola.ecommerce.application.ports.out;

import com.keola.ecommerce.domain.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPersistencePort {
    Mono<Product> createOneProduct(Product product);
    Flux<Product> findProducts();
    Mono<Product> findOneProduct(Integer productId);
    Mono<Product> findOneProductByName(String name);
    Mono<Boolean> existsOneProduct(Integer productId);
    Mono<Boolean> existsOneProductByName(String name);
    Mono<Void> updateOneProduct(Integer productId, Product product);
    Mono<Void> deleteOneProduct(Integer productId);
}
