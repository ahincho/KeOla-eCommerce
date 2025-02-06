package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders;

import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private ProductResponse product;
    private Integer quantity;
}
