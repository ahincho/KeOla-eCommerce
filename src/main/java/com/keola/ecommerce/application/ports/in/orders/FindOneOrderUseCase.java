package com.keola.ecommerce.application.ports.in.orders;

import com.keola.ecommerce.domain.models.Order;

import reactor.core.publisher.Mono;

public interface FindOneOrderUseCase {
    Mono<Order> execute(String orderId);
}
