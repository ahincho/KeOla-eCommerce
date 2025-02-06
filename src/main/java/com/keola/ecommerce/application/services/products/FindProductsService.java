package com.keola.ecommerce.application.services.products;

import com.keola.ecommerce.application.ports.in.products.FindProductsUseCase;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.models.Product;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FindProductsService implements FindProductsUseCase {
    private final ProductPersistencePort productPersistencePort;
    public FindProductsService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Flux<Product> execute() {
        return this.productPersistencePort.findProducts();
    }
}
