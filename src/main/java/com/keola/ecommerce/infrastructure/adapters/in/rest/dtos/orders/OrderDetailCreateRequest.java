package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailCreateRequest {
    @NotNull
    @Positive
    private Integer productId;
    @NotNull
    @Positive
    private Integer quantity;
}
