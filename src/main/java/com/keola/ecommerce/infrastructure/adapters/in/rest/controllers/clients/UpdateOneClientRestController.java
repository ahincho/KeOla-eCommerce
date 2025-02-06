package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.clients;

import com.keola.ecommerce.application.ports.in.clients.UpdateOneClientUseCase;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientUpdateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.mappers.ClientRestMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
public class UpdateOneClientRestController {
    private final UpdateOneClientUseCase updateOneClientUseCase;
    public UpdateOneClientRestController(UpdateOneClientUseCase updateOneClientUseCase) {
        this.updateOneClientUseCase = updateOneClientUseCase;
    }
    @PatchMapping("/{clientId}")
    public Mono<Void> updateOneClient(
            @PathVariable Integer clientId,
            @RequestBody @Valid ClientUpdateRequest clientUpdateRequest
    ) {
        return this.updateOneClientUseCase.execute(clientId, ClientRestMapper.updateRequestToDomain(clientId, clientUpdateRequest));
    }
}
