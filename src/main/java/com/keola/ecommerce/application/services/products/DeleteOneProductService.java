package com.keola.ecommerce.application.services.products;

import com.keola.ecommerce.application.ports.in.products.DeleteOneProductUseCase;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.exceptions.ProductNotFoundException;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DeleteOneProductService implements DeleteOneProductUseCase {
    private final ProductPersistencePort productPersistencePort;
    public DeleteOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Mono<Void> execute(Integer productId) {
        return this.productPersistencePort.existsOneProduct(productId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ProductNotFoundException("Product with id " + productId + " not found"));
                    }
                    return this.productPersistencePort.deleteOneProduct(productId);
                });
    }
}
