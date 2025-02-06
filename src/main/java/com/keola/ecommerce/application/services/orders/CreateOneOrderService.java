package com.keola.ecommerce.application.services.orders;

import com.keola.ecommerce.application.ports.in.orders.CreateOneOrderUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.application.ports.out.OrderPersistencePort;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.exceptions.ClientNotFoundException;
import com.keola.ecommerce.domain.exceptions.ProductNotFoundException;
import com.keola.ecommerce.domain.models.Order;
import com.keola.ecommerce.domain.models.OrderDetail;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class CreateOneOrderService implements CreateOneOrderUseCase {
    private final ClientPersistencePort clientPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    private final OrderPersistencePort orderPersistencePort;
    public CreateOneOrderService(
        ClientPersistencePort clientPersistencePort,
        ProductPersistencePort productPersistencePort,
        OrderPersistencePort orderPersistencePort
    ) {
        this.clientPersistencePort = clientPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public Mono<Order> execute(Order order) {
        return this.clientPersistencePort.existsOneClientById(order.getClient())
                .flatMap(clientExists -> {
                    if (!clientExists) {
                        return Mono.error(new ClientNotFoundException("Client with id " + order.getClient() + " not found"));
                    }
                    return validateProductExistence(order.getOrderDetails())
                            .flatMap(allProductsExist -> {
                                if (!allProductsExist) {
                                    return Mono.error(new ProductNotFoundException("One or more products not found"));
                                }
                                return this.orderPersistencePort.createOneOrder(order);
                            });
                });
    }
    private Mono<Boolean> validateProductExistence(Set<OrderDetail> orderDetails) {
        return Mono.just(orderDetails)
                .flatMap(details -> {
                    Mono<Boolean> allProductsExist = Mono.just(true);
                    for (OrderDetail orderDetail : details) {
                        Mono<Boolean> productExists = productPersistencePort.existsOneProduct(orderDetail.getProductId());
                        allProductsExist = allProductsExist.zipWith(productExists, (existsSoFar, existsNow) -> existsSoFar && existsNow);
                    }
                    return allProductsExist;
                });
    }
}
