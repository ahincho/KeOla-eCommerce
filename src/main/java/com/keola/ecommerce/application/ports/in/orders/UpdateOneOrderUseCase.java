package com.keola.ecommerce.application.ports.in.orders;

import com.keola.ecommerce.domain.models.Order;

import reactor.core.publisher.Mono;

public interface UpdateOneOrderUseCase {
    Mono<Void> execute(String orderId, Order order);
}
