package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.clients;

import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ClientR2dbcPersistenceAdapter implements ClientPersistencePort {
    private final ClientR2dbcRepository clientR2dbcRepository;
    public ClientR2dbcPersistenceAdapter(ClientR2dbcRepository clientR2dbcRepository) {
        this.clientR2dbcRepository = clientR2dbcRepository;
    }
    @Override
    @Transactional
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
    public Mono<Client> findOneClient(Integer clientId) {
        return this.clientR2dbcRepository.findById(clientId).map(ClientPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Client> findOneClientByEmail(String email) {
        return this.clientR2dbcRepository.findByEmail(email).map(ClientPersistenceMapper::entityToDomain);
    }
    @Override
    public Mono<Boolean> existsOneClientById(Integer clientId) {
        return this.clientR2dbcRepository.existsById(clientId);
    }
    @Override
    public Mono<Boolean> existsOneClientByEmail(String email) {
        return this.clientR2dbcRepository.existsByEmail(email);
    }
    @Override
    @Transactional
    public Mono<Void> updateOneClient(Integer clientId, Client client) {
        return this.clientR2dbcRepository.findById(clientId)
                .flatMap(existingClient -> {
                    ClientEntity updatedClient = ClientEntity.builder()
                            .id(existingClient.getId())
                            .name(Optional.ofNullable(client.getName()).orElse(existingClient.getName()))
                            .lastname(Optional.ofNullable(client.getLastname()).orElse(existingClient.getLastname()))
                            .email(Optional.ofNullable(client.getEmail()).orElse(existingClient.getEmail()))
                            .createdAt(existingClient.getCreatedAt())
                            .updatedAt(LocalDateTime.now())
                            .build();
                    return this.clientR2dbcRepository.save(updatedClient).then();
                });
    }
    @Override
    @Transactional
    public Mono<Void> deleteOneClient(Integer clientId) {
        return this.clientR2dbcRepository.deleteById(clientId);
    }
}
