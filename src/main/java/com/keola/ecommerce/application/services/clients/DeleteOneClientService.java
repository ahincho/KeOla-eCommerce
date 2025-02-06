package com.keola.ecommerce.application.services.clients;

import com.keola.ecommerce.application.ports.in.clients.DeleteOneClientUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.exceptions.ClientNotFoundException;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DeleteOneClientService implements DeleteOneClientUseCase {
    private final ClientPersistencePort clientPersistencePort;
    public DeleteOneClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }
    @Override
    public Mono<Void> execute(Integer clientId) {
        return this.clientPersistencePort.existsOneClientById(clientId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ClientNotFoundException("Client with id " + clientId + " not found"));
                    }
                    return this.clientPersistencePort.deleteOneClient(clientId);
                });
    }
}
