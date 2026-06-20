package com.example.multa.controller;

import com.example.multa.dto.MultaDTO;
import com.example.multa.service.MultaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MultaController.class)
class MultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MultaService service;

    @Test
    void obtenerMultaPorId() throws Exception {

        MultaDTO dto = new MultaDTO(
                1L,
                "Don Quijote",
                "Bastian",
                5000.0,
                5,
                "PENDIENTE",
                LocalDate.now()
        );

        Mockito.when(service.findDtoById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/multas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prestamo").value("Don Quijote"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }
}