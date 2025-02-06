package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.clients;

import com.keola.ecommerce.application.ports.in.clients.CreateOneClientUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ClientRestMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
public class CreateOneClientRestController {
    private final CreateOneClientUseCase createOneClientUseCase;
    public CreateOneClientRestController(CreateOneClientUseCase createOneClientUseCase) {
        this.createOneClientUseCase = createOneClientUseCase;
    }
    @PostMapping
    public Mono<ClientResponse> createOneClient(@RequestBody @Valid ClientCreateRequest clientCreateRequest) {
        return this.createOneClientUseCase.execute(ClientRestMapper.createRequestToDomain(clientCreateRequest))
                .map(ClientRestMapper::domainToResponse);
    }
}
