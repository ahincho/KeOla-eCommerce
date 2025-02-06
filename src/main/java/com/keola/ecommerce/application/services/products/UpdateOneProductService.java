package com.keola.ecommerce.application.services.products;

import com.keola.ecommerce.application.ports.in.products.UpdateOneProductUseCase;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.exceptions.ProductDuplicateException;
import com.keola.ecommerce.domain.exceptions.ProductNotFoundException;
import com.keola.ecommerce.domain.models.Product;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UpdateOneProductService implements UpdateOneProductUseCase {
    private final ProductPersistencePort productPersistencePort;
    public UpdateOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Mono<Void> execute(Integer productId, Product product) {
        return this.productPersistencePort.existsOneProduct(productId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ProductNotFoundException("Product with id " + productId + " not found"));
                    }
                    Mono<Boolean> nameCheck = Mono.just(false);
                    if (product.getName() != null) {
                        nameCheck = this.productPersistencePort.findOneProductByName(product.getName())
                                .map(existingProduct -> !existingProduct.getId().equals(productId))
                                .defaultIfEmpty(false);
                    }
                    return nameCheck.flatMap(nameExists -> {
                        if (nameExists) {
                            return Mono.error(new ProductDuplicateException("Product with name " + product.getName() + " already exists"));
                        }
                        return this.productPersistencePort.updateOneProduct(productId, product);
                    });
                });
    }
}
