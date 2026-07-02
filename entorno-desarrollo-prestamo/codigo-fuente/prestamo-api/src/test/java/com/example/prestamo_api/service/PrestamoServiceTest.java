package com.example.prestamo_api.service;

import com.example.prestamo_api.client.InventarioClient;
import com.example.prestamo_api.client.LibroClient;
import com.example.prestamo_api.client.UsuarioClient;
import com.example.prestamo_api.dto.*;
import com.example.prestamo_api.exception.RecursoNoEncontradoException;
import com.example.prestamo_api.model.Prestamo;
import com.example.prestamo_api.repository.PrestamoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceTest {

    @Mock
    private PrestamoRepository repository;

    @Mock
    private LibroClient libroClient;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private InventarioClient inventarioClient;

    @InjectMocks
    private PrestamoService service;

    @Test
    void crearPrestamoCorrectamente() {

        PrestamoCreateDTO dto = new PrestamoCreateDTO();
        dto.setLibroId(1L);
        dto.setUsuarioId(1L);
        dto.setFechaPrestamo(LocalDate.now());
        dto.setFechaDevolucion(LocalDate.now().plusDays(7));
        dto.setEstado("ACTIVO");

        LibroDTO libro = new LibroDTO(1L, 1L, "Clean Code", "Robert C. Martin", "48935347574");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNombre("Bastian");

        InventarioDTO inventario = new InventarioDTO(1L, 1L, 5, "A1", "Disponible");

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(1L);
        prestamo.setUsuarioId(1L);
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setEstado("ACTIVO");

        when(libroClient.getLibroById(1L)).thenReturn(libro);
        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuario);
        when(inventarioClient.getInventarioById(1L)).thenReturn(inventario);
        when(repository.save(any(Prestamo.class))).thenReturn(prestamo);

        PrestamoDTO resultado = service.crearPrestamo(dto);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getLibro());
        assertEquals("Bastian", resultado.getUsuario());
    }

    @Test
    void noDebeCrearPrestamoSinStock() {

        PrestamoCreateDTO dto = new PrestamoCreateDTO();
        dto.setLibroId(1L);
        dto.setUsuarioId(1L);
        dto.setFechaPrestamo(LocalDate.now());
        dto.setFechaDevolucion(LocalDate.now().plusDays(7));
        dto.setEstado("ACTIVO");

        LibroDTO libro = new LibroDTO(1L, 1L, "Clean Code", "Robert C. Martin", "48935347574");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNombre("Bastian");

        InventarioDTO inventario = new InventarioDTO(1L, 1L, 0, "A1", "Disponible");

        when(libroClient.getLibroById(1L)).thenReturn(libro);
        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuario);
        when(inventarioClient.getInventarioById(1L)).thenReturn(inventario);

        assertThrows(
                RecursoNoEncontradoException.class,
                () -> service.crearPrestamo(dto)
        );
    }

    @Test
    void buscarPrestamoPorIdCorrectamente() {

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(1L);
        prestamo.setUsuarioId(1L);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamo.setEstado("ACTIVO");

        LibroDTO libro = new LibroDTO(
                1L,
                1L,
                "Clean Code",
                "Robert C. Martin",
                "48935347574"
        );

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNombre("Bastian");

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(prestamo));
        when(libroClient.getLibroById(1L)).thenReturn(libro);
        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuario);

        PrestamoDTO resultado = service.findDtoById(1L);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getLibro());
        assertEquals("Bastian", resultado.getUsuario());
    }

    @Test
    void eliminarPrestamoCorrectamente() {

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(1L);
        prestamo.setUsuarioId(1L);

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(prestamo));

        service.eliminarPrestamo(1L);

        verify(repository, times(1)).delete(prestamo);
    }

    @Test
    void noDebeBuscarPrestamoSiNoExiste() {

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                RecursoNoEncontradoException.class,
                () -> service.findDtoById(1L)
        );
    }
}