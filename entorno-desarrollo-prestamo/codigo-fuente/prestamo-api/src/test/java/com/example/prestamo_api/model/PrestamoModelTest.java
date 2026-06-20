package com.example.prestamo_api.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoModelTest {

    @Test
    void testGettersAndSetters() {

        Prestamo prestamo = new Prestamo();

        prestamo.setLibroId(1L);
        prestamo.setUsuarioId(2L);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamo.setEstado("ACTIVO");

        assertEquals(1L, prestamo.getLibroId());
        assertEquals(2L, prestamo.getUsuarioId());
        assertEquals("ACTIVO", prestamo.getEstado());
    }
}