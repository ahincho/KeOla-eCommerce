package com.keola.ecommerce.application.services.clients;

import com.keola.ecommerce.application.ports.in.clients.CreateOneClientUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.exceptions.ClientDuplicateException;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CreateOneClientService implements CreateOneClientUseCase {
    private final ClientPersistencePort clientPersistencePort;
    public CreateOneClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }
    @Override
    public Mono<Client> execute(Client client) {
        return this.clientPersistencePort.existsOneClientByEmail(client.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ClientDuplicateException("Client with email " + client.getEmail() + " already exists"));
                    }
                    return this.clientPersistencePort.createOneClient(client);
                });
    }
}
