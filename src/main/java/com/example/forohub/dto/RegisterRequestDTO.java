package com.example.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email
) {
}
