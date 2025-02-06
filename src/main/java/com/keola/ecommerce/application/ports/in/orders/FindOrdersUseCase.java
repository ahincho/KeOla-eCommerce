package com.keola.ecommerce.application.ports.in.orders;

import com.keola.ecommerce.domain.models.Order;

import reactor.core.publisher.Flux;

public interface FindOrdersUseCase {
    Flux<Order> execute();
}
