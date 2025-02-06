package com.keola.ecommerce.application.services.clients;

import com.keola.ecommerce.application.ports.in.clients.FindClientsUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FindClientsService implements FindClientsUseCase {
    private final ClientPersistencePort clientPersistencePort;
    public FindClientsService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }
    @Override
    public Flux<Client> execute() {
        return this.clientPersistencePort.findClients();
    }
}
