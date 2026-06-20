package com.example.multa.service;

import com.example.multa.client.PrestamoClient;
import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.dto.PrestamoDTO;
import com.example.multa.exception.RecursoNoEncontradoException;
import com.example.multa.model.Multa;
import com.example.multa.repository.MultaRepository;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultaServiceTest {

    @Mock
    private MultaRepository repository;

    @Mock
    private PrestamoClient prestamoClient;

    @InjectMocks
    private MultaService service;

    @Test
    void crearMultaCorrectamente() {

        MultaCreateDTO dto = new MultaCreateDTO();
        dto.setPrestamoId(1L);
        dto.setUsuarioId(1L);
        dto.setMonto(5000.0);
        dto.setDiasRetraso(5);
        dto.setEstado("PENDIENTE");
        dto.setFechaMulta(LocalDate.now());

        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setId(1L);
        prestamo.setLibro("Clean Code");
        prestamo.setUsuario("Bastian");

        Multa multa = new Multa();
        multa.setPrestamoId(1L);
        multa.setUsuarioId(1L);
        multa.setMonto(5000.0);
        multa.setDiasRetraso(5);
        multa.setEstado("PENDIENTE");
        multa.setFechaMulta(dto.getFechaMulta());

        when(prestamoClient.getPrestamoById(1L)).thenReturn(prestamo);
        when(repository.save(any(Multa.class))).thenReturn(multa);

        MultaDTO resultado = service.crearMulta(dto);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getPrestamo());
        assertEquals("Bastian", resultado.getUsuario());
    }

    @Test
    void noDebeCrearMultaSiPrestamoNoExiste() {

        MultaCreateDTO dto = new MultaCreateDTO();
        dto.setPrestamoId(99L);
        dto.setUsuarioId(1L);
        dto.setMonto(5000.0);
        dto.setDiasRetraso(5);
        dto.setEstado("PENDIENTE");
        dto.setFechaMulta(LocalDate.now());

        when(prestamoClient.getPrestamoById(99L))
                .thenThrow(FeignException.NotFound.class);

        assertThrows(
                RecursoNoEncontradoException.class,
                () -> service.crearMulta(dto)
        );
    }
}