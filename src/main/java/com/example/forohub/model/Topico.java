package com.example.forohub.model;

import com.example.forohub.dto.ActualizarTopicoRequestDTO;
import com.example.forohub.dto.TopicoRequestDTO;
import jakarta.persistence.*;

@Entity
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private boolean publicado;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private Long idUsuario;

    public Topico(){}

    public Topico(TopicoRequestDTO topicoRequestDTO) {
        this.titulo = topicoRequestDTO.titulo();
        this.mensaje = topicoRequestDTO.mensaje();
        this.curso = topicoRequestDTO.curso();
        this.publicado = true;
        this.idUsuario = topicoRequestDTO.idUsuario();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void actualizarDatos(ActualizarTopicoRequestDTO nuevosDatosTopico) {
        this.mensaje = nuevosDatosTopico.mensaje();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", publicado=" + publicado +
                ", curso=" + curso +
                '}';
    }
}
