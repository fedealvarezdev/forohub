package com.example.forohub.dto;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoRequestDTO(
    @NotNull
    Long id,
    @NotNull
    String mensaje
    ){
}