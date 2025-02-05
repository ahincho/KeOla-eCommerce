package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers;

import com.keola.ecommerce.application.ports.in.FindClientsUseCase;
import com.keola.ecommerce.domain.models.Client;

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
    public Flux<Client> findClients() {
        return this.findClientsUseCase.execute();
    }
}
