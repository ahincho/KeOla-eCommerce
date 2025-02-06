package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.products;

import com.keola.ecommerce.domain.models.Product;

import java.time.LocalDateTime;

public class ProductPersistenceMapper {
    private ProductPersistenceMapper() {}
    public static ProductEntity domainToEntity(Product product) {
        return ProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt() == null ? LocalDateTime.now() : product.getCreatedAt())
                .updatedAt(product.getUpdatedAt() == null ? LocalDateTime.now() : product.getUpdatedAt())
                .build();
    }
    public static Product entityToDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }
}
