package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRequest {
    @Size(min = 2, max = 64)
    private String name;
    @Size(min = 2, max = 64)
    private String lastname;
    @Email
    @Size(min = 2, max = 64)
    private String email;
}
