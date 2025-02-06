package com.keola.ecommerce.application.services.products;

import com.keola.ecommerce.application.ports.in.products.FindOneProductUseCase;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.exceptions.ProductNotFoundException;
import com.keola.ecommerce.domain.models.Product;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class FindOneProductService implements FindOneProductUseCase {
    private final ProductPersistencePort productPersistencePort;
    public FindOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Mono<Product> execute(Integer productId) {
        return this.productPersistencePort
                .findOneProduct(productId)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with id " + productId + " not found")));
    }
}
