package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.products;

import com.keola.ecommerce.application.ports.in.products.FindProductsUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/products")
public class FindProductsRestController {
    private final FindProductsUseCase findProductsUseCase;
    public FindProductsRestController(FindProductsUseCase findProductsUseCase) {
        this.findProductsUseCase = findProductsUseCase;
    }
    @GetMapping
    public Flux<ProductResponse> getProducts() {
        return this.findProductsUseCase.execute().map(ProductRestMapper::domainToResponse);
    }
}
