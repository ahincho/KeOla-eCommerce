package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.orders;

import com.keola.ecommerce.application.ports.in.orders.DeleteOneOrderUseCase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
public class DeleteOneOrderRestController {
    private final DeleteOneOrderUseCase deleteOneOrderUseCase;
    public DeleteOneOrderRestController(DeleteOneOrderUseCase deleteOneOrderUseCase) {
        this.deleteOneOrderUseCase = deleteOneOrderUseCase;
    }
    @DeleteMapping("{orderId}")
    public Mono<Void> deleteOneOrder(@PathVariable String orderId) {
        return this.deleteOneOrderUseCase.execute(orderId);
    }
}
