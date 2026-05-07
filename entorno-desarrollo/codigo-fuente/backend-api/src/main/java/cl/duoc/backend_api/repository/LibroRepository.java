package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository indica a Spring que este componente se encarga de la base de datos.
 * Al heredar de JpaRepository, le decimos qué clase manejar (Libro) y qué tipo de dato es su ID (Long).
 * Esto nos regala automáticamente métodos como save(), findAll(), findById(), deleteById().
 */
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}