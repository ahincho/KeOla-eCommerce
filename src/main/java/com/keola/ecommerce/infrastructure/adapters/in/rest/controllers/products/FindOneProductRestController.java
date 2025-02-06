package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.products;

import com.keola.ecommerce.application.ports.in.products.FindOneProductUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;

import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
public class FindOneProductRestController {
    private final FindOneProductUseCase findOneProductUseCase;
    public FindOneProductRestController(FindOneProductUseCase findOneProductUseCase) {
        this.findOneProductUseCase = findOneProductUseCase;
    }
    @GetMapping("/{productId}")
    public Mono<ProductResponse> findOneProduct(@PathVariable Integer productId) {
        return this.findOneProductUseCase.execute(productId).map(ProductRestMapper::domainToResponse);
    }
}
