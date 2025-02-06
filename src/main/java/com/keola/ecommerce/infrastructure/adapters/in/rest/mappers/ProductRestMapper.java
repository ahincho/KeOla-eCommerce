package com.keola.ecommerce.infrastructure.adapters.in.rest.mappers;

import com.keola.ecommerce.domain.models.Product;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;

import java.time.LocalDateTime;

public class ProductRestMapper {
    private ProductRestMapper() {}
    public static ProductResponse domainToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
    public static Product createRequestToDomain(ProductCreateRequest productCreateRequest) {
        return Product.builder()
                .name(productCreateRequest.getName())
                .price(productCreateRequest.getPrice())
                .build();
    }
    public static Product updateRequestToDomain(Integer productId, ProductUpdateRequest productUpdateRequest) {
        return Product.builder()
                .id(productId)
                .name(productUpdateRequest.getName())
                .price(productUpdateRequest.getPrice())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
