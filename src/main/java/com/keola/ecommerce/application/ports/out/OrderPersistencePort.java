package com.keola.ecommerce.application.ports.out;

import com.keola.ecommerce.domain.models.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderPersistencePort {
    Mono<Order> createOneOrder(Order order);
    Flux<Order> findOrders();
    Mono<Order> findOneOrder(String orderId);
    Mono<Boolean> existsOneOrder(String orderId);
    Mono<Void> updateOneOrder(String orderId, Order order);
    Mono<Void> deleteOneOrder(String orderId);
}
