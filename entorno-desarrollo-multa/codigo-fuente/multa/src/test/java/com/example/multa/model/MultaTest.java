package com.example.multa.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MultaModelTest {

    @Test
    void testGettersAndSetters() {

        Multa multa = new Multa();

        multa.setPrestamoId(1L);
        multa.setUsuarioId(2L);
        multa.setMonto(5000.0);
        multa.setDiasRetraso(5);
        multa.setEstado("PENDIENTE");
        multa.setFechaMulta(LocalDate.now());

        assertEquals(1L, multa.getPrestamoId());
        assertEquals(2L, multa.getUsuarioId());
        assertEquals(5000.0, multa.getMonto());
        assertEquals(5, multa.getDiasRetraso());
        assertEquals("PENDIENTE", multa.getEstado());
    }
}