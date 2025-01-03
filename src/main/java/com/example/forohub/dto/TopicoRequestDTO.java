package com.example.forohub.dto;

import com.example.forohub.model.Curso;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(
        @NotNull(message = "{id_usuario.obligatorio}")
        @JsonAlias("id_usuario")
        Long idUsuario,

        @NotNull(message = "{mensaje.obligatorio}")
        String titulo,

        @NotNull(message = "{mensaje.obligatorio}")
        String mensaje,

        @NotNull
        @JsonAlias("nombre_curso")
        Curso curso
) {

        @Override
        public String toString() {
                return "TopicoRequestDTO{" +
                        "idUsuario=" + idUsuario +
                        ", titulo='" + titulo + '\'' +
                        ", mensaje='" + mensaje + '\'' +
                        ", curso=" + curso +
                        '}';
        }
}
