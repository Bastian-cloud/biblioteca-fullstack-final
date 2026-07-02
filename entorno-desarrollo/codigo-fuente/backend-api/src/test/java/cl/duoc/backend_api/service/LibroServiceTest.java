package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.exception.RecursoNoEncontradoException;
import cl.duoc.backend_api.model.Libro;
import cl.duoc.backend_api.repository.LibroRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository repository;

    @InjectMocks
    private LibroService service;

    @Test
    void findDtoById_deberiaRetornarDtoCuandoExiste() {
        Libro libro = new Libro();
        libro.setTitulo("Clean Code");
        libro.setAutor("Robert C. Martin");
        libro.setIsbn("978-0132350884");

        when(repository.findById(1L)).thenReturn(Optional.of(libro));

        LibroDTO resultado = service.findDtoById(1L);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getTitulo());
        assertEquals("Robert C. Martin", resultado.getAutor());
    }

    @Test
    void findDtoById_deberiaLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> service.findDtoById(99L));
    }
}