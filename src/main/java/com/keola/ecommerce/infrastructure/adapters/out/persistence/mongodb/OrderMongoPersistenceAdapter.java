package com.keola.ecommerce.infrastructure.adapters.out.persistence.mongodb;

import com.keola.ecommerce.application.ports.out.OrderPersistencePort;
import com.keola.ecommerce.domain.models.Order;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class OrderMongoPersistenceAdapter implements OrderPersistencePort {
    private final OrderMongoRepository orderMongoRepository;
    public OrderMongoPersistenceAdapter(OrderMongoRepository orderMongoRepository) {
        this.orderMongoRepository = orderMongoRepository;
    }
    @Override
    public Mono<Order> createOneOrder(Order order) {
        return this.orderMongoRepository
                .save(OrderPersistenceMapper.domainToDocument(order))
                .map(OrderPersistenceMapper::documentToDomain);
    }
    @Override
    public Flux<Order> findOrders() {
        return this.orderMongoRepository.findAll().map(OrderPersistenceMapper::documentToDomain);
    }
    @Override
    public Mono<Order> findOneOrder(String orderId) {
        return this.orderMongoRepository.findById(orderId).map(OrderPersistenceMapper::documentToDomain);
    }
    @Override
    public Mono<Boolean> existsOneOrder(String orderId) {
        return this.orderMongoRepository.existsById(orderId);
    }
    @Override
    public Mono<Void> updateOneOrder(String orderId, Order order) {
        return null;
    }
    @Override
    public Mono<Void> deleteOneOrder(String orderId) {
        return this.orderMongoRepository.deleteById(orderId);
    }
}
