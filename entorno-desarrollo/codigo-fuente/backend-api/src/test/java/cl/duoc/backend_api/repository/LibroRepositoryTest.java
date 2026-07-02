package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Libro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibroRepositoryTest {

    @Autowired
    private LibroRepository repository;

    @Test
    void save_deberiaPersistirLibroCorrectamente() {
        Libro libro = new Libro();
        libro.setTitulo("Effective Java");
        libro.setAutor("Joshua Bloch");
        libro.setIsbn("978-0134685991");

        Libro guardado = repository.save(libro);

        assertNotNull(guardado.getId());
        assertEquals("Effective Java", guardado.getTitulo());
    }

    @Test
    void findById_deberiaRetornarVacioCuandoNoExiste() {
        Optional<Libro> resultado = repository.findById(999L);

        assertTrue(resultado.isEmpty());
    }
}