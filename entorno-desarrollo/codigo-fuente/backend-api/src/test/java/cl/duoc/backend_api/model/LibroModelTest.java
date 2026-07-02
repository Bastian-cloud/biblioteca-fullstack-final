package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void settersYGetters_deberianAsignarYRetornarValoresCorrectamente() {
        Libro libro = new Libro();

        libro.setTitulo("Clean Code");
        libro.setAutor("Robert C. Martin");
        libro.setIsbn("978-0132350884");

        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals("978-0132350884", libro.getIsbn());
    }

    @Test
    void constructorVacio_deberiaCrearInstanciaConIdNulo() {
        Libro libro = new Libro();

        assertNull(libro.getId());
    }
}