package com.example.inventario_api.repository;

import com.example.inventario_api.model.Inventario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InventarioRepositoryTest {

    @Autowired
    private InventarioRepository repository;

    @Test
    void save_deberiaPersistirInventarioCorrectamente() {
        Inventario inventario = new Inventario();
        inventario.setLibroId(20L);
        inventario.setStock(15);
        inventario.setUbicacion("Estante B");
        inventario.setEstado("DISPONIBLE");

        Inventario guardado = repository.save(inventario);

        assertNotNull(guardado.getId());
        assertEquals(20L, guardado.getLibroId());
    }

    @Test
    void findById_deberiaRetornarVacioCuandoNoExiste() {
        Optional<Inventario> resultado = repository.findById(999L);

        assertTrue(resultado.isEmpty());
    }
}