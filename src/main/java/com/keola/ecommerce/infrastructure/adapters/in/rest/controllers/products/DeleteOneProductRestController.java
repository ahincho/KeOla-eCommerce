package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.products;

import com.keola.ecommerce.application.ports.in.products.DeleteOneProductUseCase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
public class DeleteOneProductRestController {
    private final DeleteOneProductUseCase deleteOneProductUseCase;
    public DeleteOneProductRestController(DeleteOneProductUseCase deleteOneProductUseCase) {
        this.deleteOneProductUseCase = deleteOneProductUseCase;
    }
    @DeleteMapping("/{productId}")
    public Mono<Void> deleteOneProduct(@PathVariable Integer productId) {
        return this.deleteOneProductUseCase.execute(productId);
    }
}
