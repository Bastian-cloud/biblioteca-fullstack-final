package com.example.inventario_api.controller;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.service.InventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getInventario_deberiaRetornar200ConInventarioExistente() throws Exception {
        InventarioDTO dto = new InventarioDTO(1L, 10L, 5, "Estante A", "DISPONIBLE");

        when(service.findDtoById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/inventario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.libroId").value(10L))
                .andExpect(jsonPath("$.stock").value(5));
    }

    @Test
    void crearInventario_deberiaRetornar201ConInventarioCreado() throws Exception {
        InventarioCreateDTO createDto = new InventarioCreateDTO();
        createDto.setLibroId(10L);
        createDto.setStock(5);
        createDto.setUbicacion("Estante A");
        createDto.setEstado("DISPONIBLE");

        InventarioDTO respuesta = new InventarioDTO(1L, 10L, 5, "Estante A", "DISPONIBLE");

        when(service.crearInventario(org.mockito.ArgumentMatchers.any(InventarioCreateDTO.class)))
                .thenReturn(respuesta);

        mockMvc.perform(post("/api/inventario")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.estado").value("DISPONIBLE"));
    }
}