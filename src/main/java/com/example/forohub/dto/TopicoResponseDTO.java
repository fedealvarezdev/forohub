package com.example.forohub.dto;

import com.example.forohub.model.Topico;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        String curso
) {

    public TopicoResponseDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso().toString());
    }
}
