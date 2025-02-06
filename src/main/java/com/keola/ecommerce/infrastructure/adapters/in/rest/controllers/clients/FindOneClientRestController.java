package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.clients;

import com.keola.ecommerce.application.ports.in.clients.FindOneClientUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ClientRestMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
public class FindOneClientRestController {
    private final FindOneClientUseCase findOneClientUseCase;
    public FindOneClientRestController(FindOneClientUseCase findOneClientUseCase) {
        this.findOneClientUseCase = findOneClientUseCase;
    }
    @GetMapping("/{clientId}")
    public Mono<ClientResponse> findOneClient(@PathVariable Integer clientId) {
        return this.findOneClientUseCase.execute(clientId).map(ClientRestMapper::domainToResponse);
    }
}
