package com.keola.ecommerce.infrastructure.adapters.out.persistence.mongodb;

import com.keola.ecommerce.domain.models.Order;
import com.keola.ecommerce.domain.models.OrderDetail;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderPersistenceMapper {
    private OrderPersistenceMapper() {}
    public static OrderDocument domainToDocument(Order order) {
        Set<OrderDetailDocument> orderDetailDocuments = order.getOrderDetails().stream()
                .map(orderDetail -> new OrderDetailDocument(orderDetail.getProductId(), orderDetail.getQuantity()))
                .collect(Collectors.toSet());
        return OrderDocument.builder()
                .id(order.getId())
                .client(order.getClient())
                .orderDetails(orderDetailDocuments)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
    public static Order documentToDomain(OrderDocument orderDocument) {
        Set<OrderDetail> orderDetails = orderDocument.getOrderDetails().stream()
                .map(orderDetailDocument -> new OrderDetail(orderDetailDocument.getProductId(), orderDetailDocument.getQuantity()))
                .collect(Collectors.toSet());
        return Order.builder()
                .id(orderDocument.getId())
                .client(orderDocument.getClient())
                .orderDetails(orderDetails)
                .createdAt(orderDocument.getCreatedAt())
                .updatedAt(orderDocument.getUpdatedAt())
                .build();
    }
}
