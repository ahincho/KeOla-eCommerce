package com.keola.ecommerce.infrastructure.adapters.out.persistence.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class OrderDocument {
    private String id;
    private Integer client;
    private Set<OrderDetailDocument> orderDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
