package com.example.usuario_api.repository;

import com.example.usuario_api.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    void save_deberiaPersistirUsuarioCorrectamente() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Camila Rojas");
        usuario.setCorreo("camila@duoc.cl");
        usuario.setRol("USUARIO");

        Usuario guardado = repository.save(usuario);

        assertNotNull(guardado.getId());
        assertEquals("Camila Rojas", guardado.getNombre());
    }

    @Test
    void findById_deberiaRetornarVacioCuandoNoExiste() {
        Optional<Usuario> resultado = repository.findById(999L);

        assertTrue(resultado.isEmpty());
    }
}