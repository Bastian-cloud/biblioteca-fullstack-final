package com.example.prestamo_api.repository;

import com.example.prestamo_api.model.Prestamo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PrestamoRepositoryTest {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Test
    void guardarPrestamo() {
        // Crear un préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(1L);
        prestamo.setUsuarioId(1L);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamo.setEstado("ACTIVO");

        // Guardar
        Prestamo guardado = prestamoRepository.save(prestamo);

        // Verificar
        assertNotNull(guardado.getId());
        assertEquals(1L, guardado.getLibroId());
        assertEquals(1L, guardado.getUsuarioId());
        assertEquals("ACTIVO", guardado.getEstado());
    }
}