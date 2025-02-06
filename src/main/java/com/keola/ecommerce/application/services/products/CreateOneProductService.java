package com.keola.ecommerce.application.services.products;

import com.keola.ecommerce.application.ports.in.products.CreateOneProductUseCase;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.exceptions.ProductDuplicateException;
import com.keola.ecommerce.domain.models.Product;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CreateOneProductService implements CreateOneProductUseCase {
    private final ProductPersistencePort productPersistencePort;
    public CreateOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Mono<Product> execute(Product product) {
        return this.productPersistencePort.existsOneProductByName(product.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ProductDuplicateException("Product with name " + product.getName() + " already exists"));
                    }
                    return this.productPersistencePort.createOneProduct(product);
                });
    }
}
