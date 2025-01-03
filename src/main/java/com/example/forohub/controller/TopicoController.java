package com.example.forohub.controller;

import com.example.forohub.dto.ActualizarTopicoRequestDTO;
import com.example.forohub.dto.TopicoResponseDTO;
import com.example.forohub.model.Topico;
import com.example.forohub.dto.TopicoRequestDTO;
import com.example.forohub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping()
    public ResponseEntity<TopicoResponseDTO> crearTopico(@RequestBody @Valid TopicoRequestDTO topicoRequestDTO, UriComponentsBuilder uriBuilder) {
        Topico topico = new Topico(topicoRequestDTO);
        topico = topicoRepository.save(topico);

        URI url = uriBuilder.path("topicos/{idTopico}").build(topico.getId());
        return ResponseEntity.created(url).body(new TopicoResponseDTO(topico));
    }

    @GetMapping()
    public ResponseEntity<Page<TopicoResponseDTO>> listar(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByPublicadoTrue(paginacion).map(TopicoResponseDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> getTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findByPublicadoTrueAndId(id);
        if (topico == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(new TopicoResponseDTO(topico));
    }


    @PutMapping()
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid ActualizarTopicoRequestDTO nuevosDatosTopico){
        Topico topico = topicoRepository.getReferenceById(nuevosDatosTopico.id());
        topico.actualizarDatos(nuevosDatosTopico);
        return ResponseEntity.ok().body(new TopicoResponseDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borradoLogicoTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.setPublicado(false);
        return ResponseEntity.noContent().build();
    }
}
