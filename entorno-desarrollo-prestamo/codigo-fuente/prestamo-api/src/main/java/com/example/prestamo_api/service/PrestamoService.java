package com.example.prestamo_api.service;

import com.example.prestamo_api.client.LibroClient;
import com.example.prestamo_api.dto.LibroDTO;
import com.example.prestamo_api.dto.PrestamoCreateDTO;
import com.example.prestamo_api.dto.PrestamoDTO;
import com.example.prestamo_api.model.Prestamo;
import com.example.prestamo_api.repository.PrestamoRepository;

import org.springframework.stereotype.Service;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;
    private final LibroClient libroClient;

    public PrestamoService(
            PrestamoRepository repository,
            LibroClient libroClient) {

        this.repository = repository;
        this.libroClient = libroClient;
    }

    // GET /api/prestamos/{id}
    public PrestamoDTO findDtoById(Long id) {

        Prestamo p = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Préstamo no encontrado"));

        // Consulta a microservicio libro
        LibroDTO libro = libroClient.getLibroById(p.getLibroId());

        return new PrestamoDTO(
                p.getId(),
                libro.getTitulo(),
                p.getUsuario(),
                p.getFechaPrestamo(),
                p.getFechaDevolucion(),
                p.getEstado()
        );
    }

    // POST /api/prestamos
    public PrestamoDTO crearPrestamo(PrestamoCreateDTO dto) {

        // Validar libro en microservicio externo
        LibroDTO libro = libroClient.getLibroById(dto.getLibroId());

        if (libro == null) {
            throw new RuntimeException("Libro no encontrado");
        }

        // Convertir DTO a entidad
        Prestamo prestamo = new Prestamo();

        prestamo.setLibroId(dto.getLibroId());
        prestamo.setUsuario(dto.getUsuario());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setEstado(dto.getEstado());

        // Guardar
        Prestamo guardado = repository.save(prestamo);

        // Retornar DTO
        return new PrestamoDTO(
                guardado.getId(),
                libro.getTitulo(),
                guardado.getUsuario(),
                guardado.getFechaPrestamo(),
                guardado.getFechaDevolucion(),
                guardado.getEstado()
        );
    }
}
