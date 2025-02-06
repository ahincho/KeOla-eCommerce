package com.keola.ecommerce.infrastructure.adapters.out.persistence.r2dbc.clients;

import com.keola.ecommerce.domain.models.Client;

import java.time.LocalDateTime;

public class ClientPersistenceMapper {
    public static ClientEntity domainToEntity(Client client) {
        return ClientEntity.builder()
                .name(client.getName())
                .lastname(client.getLastname())
                .email(client.getEmail())
                .createdAt(client.getCreatedAt() == null ? LocalDateTime.now() : client.getCreatedAt())
                .updatedAt(client.getUpdatedAt() == null ? LocalDateTime.now() : client.getUpdatedAt())
                .build();
    }
    public static Client entityToDomain(ClientEntity clientEntity) {
        return Client.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .lastname(clientEntity.getLastname())
                .email(clientEntity.getEmail())
                .createdAt(clientEntity.getCreatedAt())
                .updatedAt(clientEntity.getUpdatedAt())
                .build();
    }
}
