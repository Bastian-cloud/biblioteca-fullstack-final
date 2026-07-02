package com.example.usuario_api.service;

import com.example.usuario_api.exception.RecursoNoEncontradoException;
import com.example.usuario_api.model.Usuario;
import com.example.usuario_api.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService service;

    @Test
    void buscar_deberiaRetornarUsuarioCuandoExiste() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Bastian Mendoza");
        usuario.setCorreo("bastian@duoc.cl");
        usuario.setRol("ADMIN");

        when(repo.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscar(1L);

        assertNotNull(resultado);
        assertEquals("Bastian Mendoza", resultado.getNombre());
    }

    @Test
    void buscar_deberiaLanzarExcepcionCuandoNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> service.buscar(99L));
    }
}