package com.keola.ecommerce.infrastructure.adapters.in.rest.dtos.clients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequest {
    @NotBlank
    @Size(min = 2, max = 64, message = "Name must be between 2 and 64 characters")
    private String name;
    @NotBlank
    @Size(min = 2, max = 64, message = "Lastname must be between 2 and 64 characters")
    private String lastname;
    @NotBlank
    @Email(message = "Email must be valid")
    @Size(min = 2, max = 64, message = "Email must be between 2 and 64 characters")
    private String email;
}
