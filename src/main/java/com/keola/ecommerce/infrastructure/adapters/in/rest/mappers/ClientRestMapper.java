package com.keola.ecommerce.infrastructure.adapters.in.rest.mappers;

import com.keola.ecommerce.domain.models.Client;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientCreateRequest;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientResponse;
import com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients.ClientUpdateRequest;

import java.time.LocalDateTime;

public class ClientRestMapper {
    private ClientRestMapper() {}
    public static ClientResponse domainToResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .lastname(client.getLastname())
                .email(client.getEmail())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }
    public static Client createRequestToDomain(ClientCreateRequest clientCreateRequest) {
        return Client.builder()
                .id(null)
                .name(clientCreateRequest.getName())
                .lastname(clientCreateRequest.getLastname())
                .email(clientCreateRequest.getEmail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    public static Client updateRequestToDomain(Integer clientId, ClientUpdateRequest clientUpdateRequest) {
        return Client.builder()
                .id(clientId)
                .name(clientUpdateRequest.getName())
                .lastname(clientUpdateRequest.getLastname())
                .email(clientUpdateRequest.getEmail())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
