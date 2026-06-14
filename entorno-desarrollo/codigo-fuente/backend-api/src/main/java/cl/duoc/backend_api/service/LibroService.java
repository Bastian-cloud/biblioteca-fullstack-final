package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.exception.RecursoNoEncontradoException;
import cl.duoc.backend_api.model.Libro;
import cl.duoc.backend_api.repository.LibroRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private static final Logger log =
            LoggerFactory.getLogger(LibroService.class);

    @Autowired
    private LibroRepository repository;

    // GET
    public LibroDTO findDtoById(Long id) {

        log.info("Buscando libro con id={}", id);

        Libro l = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Libro id={} no encontrado", id);
                    return new RecursoNoEncontradoException(
                            "Libro no encontrado");
                });

        log.info("Libro encontrado correctamente. ID={}", l.getId());

        return new LibroDTO(
                l.getId(),
                l.getTitulo(),
                l.getAutor(),
                l.getIsbn()
        );
    }

    // POST
    public LibroDTO crearLibro(LibroCreateDTO dto) {

        log.info("Intentando crear libro con título={}",
                dto.getTitulo());

        try {
            Libro libro = new Libro();

            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setIsbn(dto.getIsbn());

            Libro guardado = repository.save(libro);

            log.info("Libro creado correctamente. ID={}",
                    guardado.getId());

            return new LibroDTO(
                    guardado.getId(),
                    guardado.getTitulo(),
                    guardado.getAutor(),
                    guardado.getIsbn()
            );

        } catch (Exception e) {

            log.error("Error al guardar libro: {}",
                    e.getMessage());

            throw new RuntimeException(
                    "Error interno al crear libro");
        }
    }

    // PUT
    public LibroDTO actualizarLibro(Long id, LibroCreateDTO dto) {

        log.info("Intentando actualizar libro id={}", id);

        Libro libro = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Libro id={} no encontrado para actualizar", id);
                    return new RecursoNoEncontradoException(
                            "Libro no encontrado");
                });

        try {
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setIsbn(dto.getIsbn());

            Libro actualizado = repository.save(libro);

            log.info("Libro actualizado correctamente. ID={}",
                    actualizado.getId());

            return new LibroDTO(
                    actualizado.getId(),
                    actualizado.getTitulo(),
                    actualizado.getAutor(),
                    actualizado.getIsbn()
            );

        } catch (Exception e) {

            log.error("Error al actualizar libro id={}: {}",
                    id, e.getMessage());

            throw new RuntimeException(
                    "Error interno al actualizar libro");
        }
    }

    // DELETE
    public void eliminarLibro(Long id) {

        log.info("Intentando eliminar libro id={}", id);

        Libro libro = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Libro id={} no encontrado para eliminar", id);
                    return new RecursoNoEncontradoException(
                            "Libro no encontrado");
                });

        try {
            repository.delete(libro);

            log.info("Libro eliminado correctamente. ID={}", id);

        } catch (Exception e) {

            log.error("Error al eliminar libro id={}: {}",
                    id, e.getMessage());

            throw new RuntimeException(
                    "Error interno al eliminar libro");
        }
    }
}