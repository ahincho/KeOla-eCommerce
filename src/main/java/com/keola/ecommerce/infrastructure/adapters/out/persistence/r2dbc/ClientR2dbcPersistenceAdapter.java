package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc;

import com.keola.ecommerce.application.ports.out.ClientPersistenceRepository;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientR2dbcPersistenceAdapter implements ClientPersistenceRepository {
    private final ClientR2dbcRepository clientR2dbcRepository;
    public ClientR2dbcPersistenceAdapter(ClientR2dbcRepository clientR2dbcRepository) {
        this.clientR2dbcRepository = clientR2dbcRepository;
    }
    @Override
    public Mono<Client> createOneClient(Client client) {
        return this.clientR2dbcRepository
                .save(ClientPersistenceMapper.domainToEntity(client))
                .map(ClientPersistenceMapper::entityToDomain);
    }
    @Override
    public Flux<Client> findClients() {
        return this.clientR2dbcRepository.findAll().map(ClientPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Client> findOneClient(String clientId) {
        return this.clientR2dbcRepository.findById(clientId).map(ClientPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Boolean> existsOneClientByEmail(String email) {
        return null;
    }
    @Override
    public Mono<Void> updateOneClient(String clientId, Client client) {
        return null;
    }
    @Override
    public Mono<Void> deleteOneClient(String clientId) {
        return null;
    }
}
