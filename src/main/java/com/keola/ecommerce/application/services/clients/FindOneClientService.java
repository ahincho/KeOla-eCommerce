package com.keola.ecommerce.application.services.clients;

import com.keola.ecommerce.application.ports.in.clients.FindOneClientUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.exceptions.ClientNotFoundException;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class FindOneClientService implements FindOneClientUseCase {
    private final ClientPersistencePort clientPersistencePort;
    public FindOneClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }
    @Override
    public Mono<Client> execute(Integer clientId) {
        return this.clientPersistencePort
                .findOneClient(clientId)
                .switchIfEmpty(Mono.error(new ClientNotFoundException("Client with id " + clientId + " not found")));
    }
}
