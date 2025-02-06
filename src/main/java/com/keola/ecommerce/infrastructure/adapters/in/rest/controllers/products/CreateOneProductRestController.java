package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.products;

import com.keola.ecommerce.application.ports.in.products.CreateOneProductUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
public class CreateOneProductRestController {
    private final CreateOneProductUseCase createOneProductUseCase;
    public CreateOneProductRestController(CreateOneProductUseCase createOneProductUseCase) {
        this.createOneProductUseCase = createOneProductUseCase;
    }
    @PostMapping
    public Mono<ProductResponse> createOneProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        return this.createOneProductUseCase
                .execute(ProductRestMapper.createRequestToDomain(productCreateRequest))
                .map(ProductRestMapper::domainToResponse);
    }
}
