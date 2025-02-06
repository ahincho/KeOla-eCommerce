package com.keola.ecommerce.application.services.orders;

import com.keola.ecommerce.application.ports.in.orders.FindOneOrderUseCase;
import com.keola.ecommerce.application.ports.out.OrderPersistencePort;
import com.keola.ecommerce.domain.exceptions.OrderNotFoundException;
import com.keola.ecommerce.domain.models.Order;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class FindOneOrderService implements FindOneOrderUseCase {
    private final OrderPersistencePort orderPersistencePort;
    public FindOneOrderService(OrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public Mono<Order> execute(String orderId) {
        return this.orderPersistencePort
                .findOneOrder(orderId)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("Order with id " + orderId + " not found")));
    }
}
