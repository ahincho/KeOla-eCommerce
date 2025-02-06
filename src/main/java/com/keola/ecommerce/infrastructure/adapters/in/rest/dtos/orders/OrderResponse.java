package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private String id;
    private ClientResponse clientResponse;
    private List<OrderDetailResponse> orderDetails;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
