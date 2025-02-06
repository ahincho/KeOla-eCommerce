package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.orders;

import com.keola.ecommerce.application.ports.in.orders.CreateOneOrderUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.OrderRestMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
public class CreateOneOrderRestController {
    private final CreateOneOrderUseCase createOneOrderUseCase;
    private final OrderRestMapper orderRestMapper;
    public CreateOneOrderRestController(CreateOneOrderUseCase createOneOrderUseCase, OrderRestMapper orderRestMapper) {
        this.createOneOrderUseCase = createOneOrderUseCase;
        this.orderRestMapper = orderRestMapper;
    }
    @PostMapping
    public Mono<OrderResponse> createOneOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        return this.createOneOrderUseCase
                .execute(this.orderRestMapper.createRequestToDomain(orderCreateRequest))
                .flatMap(this.orderRestMapper::domainToResponse);
    }
}
