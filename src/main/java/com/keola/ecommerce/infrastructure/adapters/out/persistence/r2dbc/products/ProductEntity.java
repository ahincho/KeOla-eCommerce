package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {
    @Id
    private Integer id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
