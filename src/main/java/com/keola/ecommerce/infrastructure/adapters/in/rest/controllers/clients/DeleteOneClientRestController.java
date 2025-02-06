package com.keola.ecommerce.infrastructure.adapters.in.rest.controllers.clients;

import com.keola.ecommerce.application.ports.in.clients.DeleteOneClientUseCase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
public class DeleteOneClientRestController {
    private final DeleteOneClientUseCase deleteOneClientUseCase;
    public DeleteOneClientRestController(DeleteOneClientUseCase deleteOneClientUseCase) {
        this.deleteOneClientUseCase = deleteOneClientUseCase;
    }
    @DeleteMapping("/{clientId}")
    public Mono<Void> deleteOneClient(@PathVariable Integer clientId) {
        return this.deleteOneClientUseCase.execute(clientId);
    }
}
