package com.example.usuario_api.controller;

import com.example.usuario_api.model.Usuario;
import com.example.usuario_api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscar_deberiaRetornar200ConUsuarioExistente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Bastian Mendoza");
        usuario.setCorreo("bastian@duoc.cl");
        usuario.setRol("ADMIN");

        when(service.buscar(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Bastian Mendoza"));
    }

    @Test
    void guardar_deberiaRetornar201ConUsuarioCreado() throws Exception {
        Usuario nuevo = new Usuario();
        nuevo.setNombre("Bastian Mendoza");
        nuevo.setCorreo("bastian@duoc.cl");
        nuevo.setRol("ADMIN");

        Usuario guardado = new Usuario();
        guardado.setId(1L);
        guardado.setNombre("Bastian Mendoza");
        guardado.setCorreo("bastian@duoc.cl");
        guardado.setRol("ADMIN");

        when(service.guardar(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("bastian@duoc.cl"));
    }
}