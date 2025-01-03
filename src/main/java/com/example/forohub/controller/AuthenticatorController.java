package com.example.forohub.controller;

import com.example.forohub.dto.*;
import com.example.forohub.model.Usuario;
import com.example.forohub.repository.UsuarioRepository;
import com.example.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthenticatorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(),
                loginDTO.password()
        );
        var authenticatedUser =  authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((Usuario) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }

    @PostMapping("/singup")
    public ResponseEntity<RegisterResponseDTO> registrar(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, UriComponentsBuilder uriBuilder){
        String hashedPassword = passwordEncoder.encode(registerRequestDTO.password());
        Usuario usuario = new Usuario(registerRequestDTO, hashedPassword);
        usuario = usuarioRepository.save(usuario);
        URI url = uriBuilder.path("usuarios/{idUsuario}").build(usuario.getId());
        return ResponseEntity.created(url).body(new RegisterResponseDTO(usuario));
    }
}
