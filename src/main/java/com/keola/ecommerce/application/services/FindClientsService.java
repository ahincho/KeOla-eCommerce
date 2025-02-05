package com.keola.ecommerce.application.services;

import com.keola.ecommerce.application.ports.in.FindClientsUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistenceRepository;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FindClientsService implements FindClientsUseCase {
    private final ClientPersistenceRepository clientPersistenceRepository;
    public FindClientsService(ClientPersistenceRepository clientPersistenceRepository) {
        this.clientPersistenceRepository = clientPersistenceRepository;
    }
    @Override
    public Flux<Client> execute() {
        return this.clientPersistenceRepository.findClients();
    }
}
