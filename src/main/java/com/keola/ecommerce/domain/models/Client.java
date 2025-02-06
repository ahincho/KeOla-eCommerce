package com.keola.ecommerce.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private Set<Order> orders;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
