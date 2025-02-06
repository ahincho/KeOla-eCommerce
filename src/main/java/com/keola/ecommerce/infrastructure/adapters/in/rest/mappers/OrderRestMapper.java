package com.keola.ecommerce.infrastructure.adapters.in.rest.mappers;

import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.application.ports.out.ProductPersistencePort;
import com.keola.ecommerce.domain.models.Client;
import com.keola.ecommerce.domain.models.Order;
import com.keola.ecommerce.domain.models.OrderDetail;
import com.keola.ecommerce.domain.models.Product;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderDetailResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.orders.OrderResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.products.ProductResponse;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderRestMapper {
    private final ClientPersistencePort clientPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    public OrderRestMapper(ClientPersistencePort clientPersistencePort, ProductPersistencePort productPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }
    public Mono<OrderResponse> domainToResponse(Order order) {
        Mono<Client> clientMono = clientPersistencePort.findOneClient(order.getClient());
        Mono<List<OrderDetailResponse>> orderDetailResponsesMono = Flux.fromIterable(order.getOrderDetails())
                .flatMap(orderDetail ->
                        productPersistencePort.findOneProduct(orderDetail.getProductId())
                                .map(product -> {
                                    Double price = (product != null) ? product.getPrice() * orderDetail.getQuantity() : 0.0;
                                    return new OrderDetailResponse(
                                            new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCreatedAt(), product.getUpdatedAt()),
                                            orderDetail.getQuantity()
                                    );
                                })
                )
                .collectList();
        return Mono.zip(clientMono, orderDetailResponsesMono)
                .map(tuple -> {
                    Client client = tuple.getT1();
                    List<OrderDetailResponse> orderDetailResponses = tuple.getT2();
                    double totalPrice = orderDetailResponses.stream()
                            .mapToDouble(orderDetailResponse -> orderDetailResponse.getProduct().getPrice() * orderDetailResponse.getQuantity())
                            .sum();
                    ClientResponse clientResponse = new ClientResponse(
                            client.getId(),
                            client.getName(),
                            client.getLastname(),
                            client.getEmail(),
                            client.getCreatedAt(),
                            client.getUpdatedAt()
                    );
                    return OrderResponse.builder()
                            .id(order.getId())
                            .clientResponse(clientResponse)
                            .orderDetails(orderDetailResponses)
                            .totalPrice(totalPrice)
                            .createdAt(order.getCreatedAt())
                            .updatedAt(order.getUpdatedAt())
                            .build();
                });
    }
    public Order createRequestToDomain(OrderCreateRequest orderCreateRequest) {
        Set<OrderDetail> orderDetails = orderCreateRequest.getOrderDetails().stream()
                .map(orderDetailRequest -> OrderDetail.builder()
                        .productId(orderDetailRequest.getProductId())
                        .quantity(orderDetailRequest.getQuantity())
                        .build())
                .collect(Collectors.toSet());
        return Order.builder()
                .client(orderCreateRequest.getClientId())
                .orderDetails(orderDetails)
                .build();
    }
}
