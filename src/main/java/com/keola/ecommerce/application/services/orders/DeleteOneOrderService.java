package com.keola.ecommerce.application.services.orders;

import com.keola.ecommerce.application.ports.in.orders.DeleteOneOrderUseCase;
import com.keola.ecommerce.application.ports.out.OrderPersistencePort;
import com.keola.ecommerce.domain.exceptions.OrderNotFoundException;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DeleteOneOrderService implements DeleteOneOrderUseCase {
    private final OrderPersistencePort orderPersistencePort;
    public DeleteOneOrderService(OrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public Mono<Void> execute(String orderId) {
        return this.orderPersistencePort.existsOneOrder(orderId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new OrderNotFoundException("Order with id " + orderId + " not found"));
                    }
                    return this.orderPersistencePort.deleteOneOrder(orderId);
                });
    }
}
