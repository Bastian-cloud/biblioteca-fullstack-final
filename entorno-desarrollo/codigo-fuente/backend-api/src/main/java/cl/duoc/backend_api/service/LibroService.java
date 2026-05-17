package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.model.Libro;
import cl.duoc.backend_api.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    // GET /api/libros/{id}
    public LibroDTO findDtoById(Long id) {

        Libro l = repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Libro no encontrado"));

        return new LibroDTO(
            l.getId(),
            l.getTitulo(),
            l.getAutor(),
            l.getIsbn()
        );
    }

    // POST /api/libros
    public LibroDTO crearLibro(LibroCreateDTO dto) {

        // 1. Convertir DTO de entrada a entidad
        Libro libro = new Libro();

        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setIsbn(dto.getIsbn());

        // 2. Guardar en BD
        Libro guardado = repository.save(libro);

        // 3. Convertir entidad guardada a DTO de salida
        return new LibroDTO(
            guardado.getId(),
            guardado.getTitulo(),
            guardado.getAutor(),
            guardado.getIsbn()
        );
    }
}