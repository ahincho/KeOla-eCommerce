package com.keola.ecommerce.infrastructure.adapters.out.persistence.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDocument {
    private Integer productId;
    private Integer quantity;
}
