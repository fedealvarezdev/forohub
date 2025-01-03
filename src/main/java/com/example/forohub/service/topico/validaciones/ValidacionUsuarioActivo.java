package com.example.forohub.service.topico.validaciones;

import com.example.forohub.dto.TopicoRequestDTO;
import com.example.forohub.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionUsuarioActivo implements IValidacion{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(TopicoRequestDTO topicoRequestDTO) {

        var usuario = usuarioRepository.findActivoById(topicoRequestDTO.idUsuario());

        if (usuario.isEmpty()){
            throw new ValidationException("Topico no puede ser creado con un usuario inactivo");
        }
    }

}
