package com.example.forohub.controller;

import com.example.forohub.dto.TopicoRequestDTO;
import com.example.forohub.dto.TopicoResponseDTO;
import com.example.forohub.model.Curso;
import com.example.forohub.service.topico.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TopicoRequestDTO> topicoRequestDTOJson;

    @Autowired
    private JacksonTester<TopicoResponseDTO> topicoResponseDTOJson;

    @MockitoBean
    private TopicoService topicoService;

    @Test
    @DisplayName("Deberia devolver http 403 cuando la request no tiene token de usuario")
    void topico_escenario1() throws Exception {
        var response = mvc.perform(post("/topicos")).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deberia devolcer http 400 cuando la request no tiene body")
    @WithMockUser
    void topico_escenario2() throws Exception {
        var response = mvc.perform(post("/topicos")).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia devolver http 200 cuando la request tienen json y token valido")
    @WithMockUser
    void topico_escenario3() throws Exception {
        var topicoResponseDTO = new TopicoResponseDTO(null, "titulo inventado", "mensaje inventado", Curso.HISTORIA.toString());

        var bodyRequest = topicoRequestDTOJson.write(
                new TopicoRequestDTO(3L, "titulo inventado", "mensaje inventado", Curso.HISTORIA)
        );

        var bodyResponse = topicoResponseDTOJson.write(
                topicoResponseDTO
        );

        when(topicoService.publicar(any())).thenReturn(topicoResponseDTO);

        var response = mvc.perform(post("/api/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest.getJson())
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), bodyResponse.getJson());
    }
}