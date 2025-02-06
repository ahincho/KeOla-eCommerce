package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.orders;

import com.keola.ecommerce.application.ports.in.orders.FindOneOrderUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.OrderRestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
public class FindOneOrderRestController {
    private final FindOneOrderUseCase findOneOrderUseCase;
    private final OrderRestMapper orderRestMapper;
    public FindOneOrderRestController(FindOneOrderUseCase findOneOrderUseCase, OrderRestMapper orderRestMapper) {
        this.findOneOrderUseCase = findOneOrderUseCase;
        this.orderRestMapper = orderRestMapper;
    }
    @GetMapping("/{orderId}")
    public Mono<OrderResponse> findOneOrder(@PathVariable String orderId) {
        return this.findOneOrderUseCase.execute(orderId).flatMap(this.orderRestMapper::domainToResponse);
    }
}
