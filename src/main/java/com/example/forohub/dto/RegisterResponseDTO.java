package com.example.forohub.dto;

import com.example.forohub.model.Usuario;

public record RegisterResponseDTO(
        Long id,
        String username
) {

    public RegisterResponseDTO(Usuario usuario){
        this(usuario.getId(),usuario.getUsername());
    }
}
