package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.products;

import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.models.Product;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ProductR2dbcPersistenceAdapter implements ProductPersistencePort {
    private final ProductR2dbcRepository productR2dbcRepository;
    public ProductR2dbcPersistenceAdapter(ProductR2dbcRepository productR2dbcRepository) {
        this.productR2dbcRepository = productR2dbcRepository;
    }
    @Override
    @Transactional
    public Mono<Product> createOneProduct(Product product) {
        return this.productR2dbcRepository
                .save(ProductPersistenceMapper.domainToEntity(product))
                .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Flux<Product> findProducts() {
        return this.productR2dbcRepository
                .findAll()
                .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Product> findOneProduct(Integer productId) {
        return this.productR2dbcRepository
                .findById(productId)
                .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Product> findOneProductByName(String name) {
        return this.productR2dbcRepository
                .findByName(name)
                .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Boolean> existsOneProduct(Integer productId) {
        return this.productR2dbcRepository.existsById(productId);
    }
    @Override
    public Mono<Boolean> existsOneProductByName(String name) {
        return this.productR2dbcRepository.existsByName(name);
    }
    @Override
    @Transactional
    public Mono<Void> updateOneProduct(Integer productId, Product product) {
        return this.productR2dbcRepository.findById(productId)
                .flatMap(existingProduct -> {
                    ProductEntity updatedProduct = ProductEntity.builder()
                            .id(existingProduct.getId())
                            .name(Optional.ofNullable(product.getName()).orElse(existingProduct.getName()))
                            .price(Optional.ofNullable(product.getPrice()).orElse(existingProduct.getPrice()))
                            .createdAt(existingProduct.getCreatedAt())
                            .updatedAt(LocalDateTime.now())
                            .build();
                    return this.productR2dbcRepository.save(updatedProduct).then();
                });
    }
    @Override
    @Transactional
    public Mono<Void> deleteOneProduct(Integer productId) {
        return this.productR2dbcRepository.deleteById(productId).then();
    }
}
