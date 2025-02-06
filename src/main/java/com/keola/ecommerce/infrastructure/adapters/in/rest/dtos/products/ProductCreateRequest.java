package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotBlank
    @Size(min = 2, max = 128)
    private String name;
    @NotNull
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999999.99")
    private Double price;
}
