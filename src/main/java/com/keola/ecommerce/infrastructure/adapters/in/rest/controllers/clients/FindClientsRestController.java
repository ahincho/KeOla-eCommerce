package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.clients;

import com.keola.ecommerce.application.ports.in.clients.FindClientsUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ClientRestMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/clients")
public class FindClientsRestController {
    private final FindClientsUseCase findClientsUseCase;
    public FindClientsRestController(FindClientsUseCase findClientsUseCase) {
        this.findClientsUseCase = findClientsUseCase;
    }
    @GetMapping
    public Flux<ClientResponse> findClients() {
        return this.findClientsUseCase.execute().map(ClientRestMapper::domainToResponse);
    }
}
