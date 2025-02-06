package com.keola.ecommerce.application.services.clients;

import com.keola.ecommerce.application.ports.in.clients.UpdateOneClientUseCase;
import com.keola.ecommerce.application.ports.out.ClientPersistencePort;
import com.keola.ecommerce.domain.exceptions.ClientDuplicateException;
import com.keola.ecommerce.domain.exceptions.ClientNotFoundException;
import com.keola.ecommerce.domain.models.Client;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UpdateOneClientService implements UpdateOneClientUseCase {
    private final ClientPersistencePort clientPersistencePort;
    public UpdateOneClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }
    @Override
    public Mono<Void> execute(Integer clientId, Client client) {
        return this.clientPersistencePort.existsOneClientById(clientId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ClientNotFoundException("Client with id " + clientId + " not found"));
                    }
                    Mono<Boolean> emailCheck = Mono.just(false);
                    if (client.getEmail() != null) {
                        emailCheck = this.clientPersistencePort.findOneClientByEmail(client.getEmail())
                                .map(existingClient -> !existingClient.getId().equals(clientId))
                                .defaultIfEmpty(false);
                    }
                    return emailCheck.flatMap(emailExists -> {
                        if (emailExists) {
                            return Mono.error(new ClientDuplicateException("Client with email " + client.getEmail() + " already exists"));
                        }
                        return this.clientPersistencePort.updateOneClient(clientId, client);
                    });
                });
    }
}
