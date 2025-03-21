package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
