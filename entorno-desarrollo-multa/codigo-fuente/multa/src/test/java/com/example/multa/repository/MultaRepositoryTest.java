package com.example.multa.repository;

import com.example.multa.model.Multa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MultaRepositoryTest {

    @Autowired
    private MultaRepository multaRepository;

    @Test
    void guardarMulta() {
        // Crear una multa
        Multa multa = new Multa();
        multa.setPrestamoId(1L);
        multa.setUsuarioId(1L);
        multa.setMonto(5000.0);
        multa.setDiasRetraso(5);
        multa.setEstado("PENDIENTE");
        multa.setFechaMulta(LocalDate.now());

        // Guardar
        Multa guardada = multaRepository.save(multa);

        // Verificar
        assertNotNull(guardada.getId());
        assertEquals(1L, guardada.getPrestamoId());
        assertEquals(1L, guardada.getUsuarioId());
        assertEquals("PENDIENTE", guardada.getEstado());
    }
}