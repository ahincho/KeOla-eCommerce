package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.orders;

import com.keola.ecommerce.application.ports.in.orders.FindOrdersUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.OrderRestMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/orders")
public class FindOrdersRestController {
    private final FindOrdersUseCase findOrdersUseCase;
    private final OrderRestMapper orderRestMapper;
    public FindOrdersRestController(FindOrdersUseCase findOrdersUseCase, OrderRestMapper orderRestMapper) {
        this.findOrdersUseCase = findOrdersUseCase;
        this.orderRestMapper = orderRestMapper;
    }
    @GetMapping
    public Flux<OrderResponse> findAll() {
        return this.findOrdersUseCase.execute().flatMap(this.orderRestMapper::domainToResponse);
    }
}
