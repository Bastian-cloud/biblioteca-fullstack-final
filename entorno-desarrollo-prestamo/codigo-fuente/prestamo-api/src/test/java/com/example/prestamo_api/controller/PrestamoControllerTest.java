package com.example.prestamo_api.controller;

import com.example.prestamo_api.dto.PrestamoDTO;
import com.example.prestamo_api.service.PrestamoService;
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

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PrestamoService service;

    @Test
    void obtenerPrestamoPorId() throws Exception {

        PrestamoDTO dto = new PrestamoDTO(
                1L,
                "Don Quijote",
                "Bastian",
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                "ACTIVO"
        );

        Mockito.when(service.findDtoById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/prestamos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libro").value("Don Quijote"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }
}