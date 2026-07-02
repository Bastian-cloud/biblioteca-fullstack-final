package com.example.inventario_api.service;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.exception.RecursoNoEncontradoException;
import com.example.inventario_api.model.Inventario;
import com.example.inventario_api.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository repository;

    @InjectMocks
    private InventarioService service;

    @Test
    void findDtoById_deberiaRetornarDtoCuandoExiste() {
        Inventario inventario = new Inventario();
        inventario.setLibroId(10L);
        inventario.setStock(5);
        inventario.setUbicacion("Estante A");
        inventario.setEstado("DISPONIBLE");

        when(repository.findById(1L)).thenReturn(Optional.of(inventario));

        InventarioDTO resultado = service.findDtoById(1L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getLibroId());
        assertEquals(5, resultado.getStock());
    }

    @Test
    void findDtoById_deberiaLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> service.findDtoById(99L));
    }
}