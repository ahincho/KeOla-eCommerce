package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.products;

import com.keola.ecommerce.application.ports.in.products.UpdateOneProductUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
public class UpdateOneProductRestController {
    private final UpdateOneProductUseCase updateOneProductUseCase;
    public UpdateOneProductRestController(UpdateOneProductUseCase updateOneProductUseCase) {
        this.updateOneProductUseCase = updateOneProductUseCase;
    }
    @PatchMapping("/{productId}")
    public Mono<Void> updateOneProduct(
            @PathVariable Integer productId,
            @RequestBody @Valid ProductUpdateRequest productUpdateRequest
    ) {
        return this.updateOneProductUseCase.execute(productId, ProductRestMapper.updateRequestToDomain(productId, productUpdateRequest));
    }
}
