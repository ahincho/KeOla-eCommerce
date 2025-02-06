package com.keola.ecommerce.application.services.orders;

import com.keola.ecommerce.application.ports.in.orders.FindOrdersUseCase;
import com.keola.ecommerce.application.ports.out.OrderPersistencePort;
import com.keola.ecommerce.domain.models.Order;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FindOrdersService implements FindOrdersUseCase {
    private final OrderPersistencePort orderPersistencePort;
    public FindOrdersService(OrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public Flux<Order> execute() {
        return this.orderPersistencePort.findOrders();
    }
}
