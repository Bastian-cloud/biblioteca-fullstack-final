package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Libro;
import cl.duoc.backend_api.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.List;
import java.util.Optional;

/**
 * @Service indica que esta clase contiene la lógica de negocio.
 */
@Service
public class LibroService {

    // Se declara el repositorio para poder usar las funciones de la base de datos.
    private final LibroRepository libroRepository;

    // Constructor para inyectar la dependencia del repositorio.
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /**
     * CREATE (Crear): Guarda un nuevo libro en la base de datos.
     */

    public Libro guardarLibro(Libro libro) {
        Objects.requireNonNull(libro, "El libro no puede ser null");
    return libroRepository.save(libro);
}

    /**
     * READ (Leer todos): Obtiene una lista con todos los libros guardados.
     */
    public List<Libro> obtenerTodos() {
        // El método findAll() trae todos los registros de la tabla
        return libroRepository.findAll();
    }

    /**
     * READ (Leer por ID): Busca un libro específico usando su identificador.
     * Devuelve un Optional porque el libro podría no existir.
     */
    public Optional<Libro> obtenerPorId(Long id) {
        Objects.requireNonNull(id, "El ID no puede ser null");
        return libroRepository.findById(id);
    }

    /**
     * UPDATE (Actualizar): Reemplaza los datos de un libro existente.
     */
    public Libro actualizarLibro(Long id, Libro detallesLibro) {
        // Primero verificamos si el libro existe en la base de datos
        Objects.requireNonNull(id, "El id no puede ser null");
        Optional<Libro> libroExistente = libroRepository.findById(id);
        
        if (libroExistente.isPresent()) {
            // Obtenemos el libro real de la base de datos
            Libro libroAActualizar = libroExistente.get();
            // Actualizamos sus datos con los nuevos datos recibidos
            libroAActualizar.setTitulo(detallesLibro.getTitulo());
            libroAActualizar.setAutor(detallesLibro.getAutor());
            libroAActualizar.setIsbn(detallesLibro.getIsbn());
            // Guardamos los cambios
            return libroRepository.save(libroAActualizar);
        } else {
            // Si no existe, retornamos nulo o podríamos lanzar un error
            return null; 
        }
    }

    /**
     * DELETE (Eliminar): Borra un libro de la base de datos usando su ID.
     */
    public boolean eliminarLibro(Long id) {
        Objects.requireNonNull(id, "El ID no puede ser null");
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}