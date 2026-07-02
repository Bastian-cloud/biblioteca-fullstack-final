package com.example.usuario_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void settersYGetters_deberianAsignarYRetornarValoresCorrectamente() {
        Usuario usuario = new Usuario();

        usuario.setNombre("Bastian Mendoza");
        usuario.setCorreo("bastian@duoc.cl");
        usuario.setRol("ADMIN");

        assertEquals("Bastian Mendoza", usuario.getNombre());
        assertEquals("bastian@duoc.cl", usuario.getCorreo());
        assertEquals("ADMIN", usuario.getRol());
    }

    @Test
    void constructorVacio_deberiaCrearInstanciaConIdNulo() {
        Usuario usuario = new Usuario();

        assertNull(usuario.getId());
    }
}