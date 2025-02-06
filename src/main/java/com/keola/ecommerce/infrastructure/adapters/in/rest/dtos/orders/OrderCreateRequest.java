package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    @NotNull
    @Positive
    private Integer clientId;
    @NotNull
    @Size(min = 1, max = 25)
    @Valid
    private Set<OrderDetailCreateRequest> orderDetails;
}
