package com.example.multa.service;

import com.example.multa.client.PrestamoClient;
import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.dto.PrestamoDTO;
import com.example.multa.exception.RecursoNoEncontradoException;
import com.example.multa.model.Multa;
import com.example.multa.repository.MultaRepository;

import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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

    @BeforeEach
    void setUp() {
        // @Value no se inyecta automáticamente con @InjectMocks,
        // así que fijamos el valor manualmente para los tests
        ReflectionTestUtils.setField(service, "valorMultaPorDia", 1000.0);
    }

    @Test
    void crearMultaCorrectamente_calculaDiasRetrasoYMonto() {

        MultaCreateDTO dto = new MultaCreateDTO();
        dto.setPrestamoId(1L);
        dto.setUsuarioId(1L);
        dto.setEstado("PENDIENTE");
        dto.setFechaMulta(LocalDate.of(2026, 7, 10));

        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setId(1L);
        prestamo.setLibro("Clean Code");
        prestamo.setUsuario("Bastian");
        prestamo.setFechaDevolucion(LocalDate.of(2026, 7, 5)); // 5 días antes de fechaMulta

        Multa multaGuardada = new Multa();
        multaGuardada.setPrestamoId(1L);
        multaGuardada.setUsuarioId(1L);
        multaGuardada.setMonto(5000.0);
        multaGuardada.setDiasRetraso(5);
        multaGuardada.setEstado("PENDIENTE");
        multaGuardada.setFechaMulta(dto.getFechaMulta());

        when(prestamoClient.getPrestamoById(1L)).thenReturn(prestamo);
        when(repository.save(any(Multa.class))).thenReturn(multaGuardada);

        MultaDTO resultado = service.crearMulta(dto);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getPrestamo());
        assertEquals("Bastian", resultado.getUsuario());
        assertEquals(5, resultado.getDiasRetraso());
        assertEquals(5000.0, resultado.getMonto());
    }

    @Test
    void noDebeCrearMultaSiPrestamoNoExiste() {

        MultaCreateDTO dto = new MultaCreateDTO();
        dto.setPrestamoId(99L);
        dto.setUsuarioId(1L);
        dto.setEstado("PENDIENTE");
        dto.setFechaMulta(LocalDate.now());

        when(prestamoClient.getPrestamoById(99L))
                .thenThrow(FeignException.NotFound.class);

        assertThrows(
                RecursoNoEncontradoException.class,
                () -> service.crearMulta(dto)
        );
    }

    @Test
    void buscarMultaPorIdCorrectamente() {

        Multa multa = new Multa();
        multa.setPrestamoId(1L);
        multa.setUsuarioId(1L);
        multa.setMonto(5000.0);
        multa.setDiasRetraso(5);
        multa.setEstado("PENDIENTE");
        multa.setFechaMulta(LocalDate.now());

        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setId(1L);
        prestamo.setLibro("Clean Code");
        prestamo.setUsuario("Bastian");

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(multa));

        when(prestamoClient.getPrestamoById(1L))
                .thenReturn(prestamo);

        MultaDTO resultado = service.findDtoById(1L);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getPrestamo());
        assertEquals("Bastian", resultado.getUsuario());
    }

    @Test
    void eliminarMultaCorrectamente() {

        Multa multa = new Multa();
        multa.setPrestamoId(1L);
        multa.setUsuarioId(1L);

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(multa));

        service.eliminarMulta(1L);

        verify(repository, times(1)).delete(multa);
    }

    @Test
    void actualizarMultaCorrectamente_recalculaDiasRetrasoYMonto() {

        MultaCreateDTO dto = new MultaCreateDTO();
        dto.setPrestamoId(1L);
        dto.setUsuarioId(1L);
        dto.setEstado("PENDIENTE");
        dto.setFechaMulta(LocalDate.of(2026, 7, 13));

        Multa multaExistente = new Multa();
        multaExistente.setPrestamoId(1L);
        multaExistente.setUsuarioId(1L);
        multaExistente.setMonto(5000.0);
        multaExistente.setDiasRetraso(5);
        multaExistente.setEstado("PENDIENTE");
        multaExistente.setFechaMulta(LocalDate.now());

        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setId(1L);
        prestamo.setLibro("Clean Code");
        prestamo.setUsuario("Bastian");
        prestamo.setFechaDevolucion(LocalDate.of(2026, 7, 5)); // 8 días antes de fechaMulta

        Multa multaActualizada = new Multa();
        multaActualizada.setPrestamoId(1L);
        multaActualizada.setUsuarioId(1L);
        multaActualizada.setMonto(8000.0);
        multaActualizada.setDiasRetraso(8);
        multaActualizada.setEstado("PENDIENTE");
        multaActualizada.setFechaMulta(dto.getFechaMulta());

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(multaExistente));

        when(prestamoClient.getPrestamoById(1L))
                .thenReturn(prestamo);

        when(repository.save(any(Multa.class)))
                .thenReturn(multaActualizada);

        MultaDTO resultado = service.actualizarMulta(1L, dto);

        assertNotNull(resultado);
        assertEquals("Bastian", resultado.getUsuario());
        assertEquals("Clean Code", resultado.getPrestamo());
        assertEquals(8, resultado.getDiasRetraso());
        assertEquals(8000.0, resultado.getMonto());
    }
}