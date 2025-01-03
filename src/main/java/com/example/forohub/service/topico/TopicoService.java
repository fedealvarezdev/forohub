package com.example.forohub.service.topico;


import com.example.forohub.dto.TopicoRequestDTO;
import com.example.forohub.dto.TopicoResponseDTO;
import com.example.forohub.model.Topico;
import com.example.forohub.model.Usuario;
import com.example.forohub.repository.TopicoRepository;
import com.example.forohub.repository.UsuarioRepository;
import com.example.forohub.service.topico.validaciones.IValidacion;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<IValidacion> validaciones;

    public TopicoResponseDTO publicar(TopicoRequestDTO topicoRequest) {
        Optional<Usuario> usuario = usuarioRepository.findById(topicoRequest.idUsuario());

        if (usuario.isEmpty()){
            throw new ValidationException("No se encontro al usuario con idUsuario: " + topicoRequest.idUsuario());
        }

        validaciones.forEach(v -> v.validar(topicoRequest));

        Topico topico = new Topico(topicoRequest);

        topicoRepository.save(topico);

        return new TopicoResponseDTO(topico);

    }
}
