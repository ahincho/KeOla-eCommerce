package com.keola.ecommerce.infrastructure.adapters.out.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMongoRepository extends ReactiveMongoRepository<OrderDocument, String> {

}
