package com.example.inventario_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    @Test
    void settersYGetters_deberianAsignarYRetornarValoresCorrectamente() {
        Inventario inventario = new Inventario();

        inventario.setLibroId(5L);
        inventario.setStock(30);
        inventario.setUbicacion("Estante C");
        inventario.setEstado("AGOTADO");

        assertEquals(5L, inventario.getLibroId());
        assertEquals(30, inventario.getStock());
        assertEquals("Estante C", inventario.getUbicacion());
        assertEquals("AGOTADO", inventario.getEstado());
    }

    @Test
    void constructorVacio_deberiaCrearInstanciaConIdNulo() {
        Inventario inventario = new Inventario();

        assertNull(inventario.getId());
    }
}