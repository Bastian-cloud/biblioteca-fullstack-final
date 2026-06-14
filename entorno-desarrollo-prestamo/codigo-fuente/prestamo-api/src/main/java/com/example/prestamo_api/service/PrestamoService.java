package com.example.prestamo_api.service;

import com.example.prestamo_api.client.LibroClient;
import com.example.prestamo_api.client.UsuarioClient;
import com.example.prestamo_api.dto.LibroDTO;
import com.example.prestamo_api.dto.PrestamoCreateDTO;
import com.example.prestamo_api.dto.PrestamoDTO;
import com.example.prestamo_api.dto.UsuarioDTO;
import com.example.prestamo_api.exception.RecursoNoEncontradoException;
import com.example.prestamo_api.exception.ServicioNoDisponibleException;
import com.example.prestamo_api.model.Prestamo;
import com.example.prestamo_api.repository.PrestamoRepository;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService {

    private static final Logger log =
            LoggerFactory.getLogger(PrestamoService.class);

    private final PrestamoRepository repository;
    private final LibroClient libroClient;
    private final UsuarioClient usuarioClient;

    public PrestamoService(
            PrestamoRepository repository,
            LibroClient libroClient,
            UsuarioClient usuarioClient) {

        this.repository = repository;
        this.libroClient = libroClient;
        this.usuarioClient = usuarioClient;
    }

    // GET
    public PrestamoDTO findDtoById(Long id) {

        log.info("Buscando préstamo con id={}", id);

        Prestamo p = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Préstamo id={} no encontrado", id);
                    return new RecursoNoEncontradoException("Préstamo no encontrado");
                });

        LibroDTO libro;
        UsuarioDTO usuario;

        try {
            log.info("Buscando libro con id={}", p.getLibroId());
            libro = libroClient.getLibroById(p.getLibroId());
            log.info("Libro encontrado: {}", libro.getTitulo());

            log.info("Buscando usuario con id={}", p.getUsuarioId());
            usuario = usuarioClient.getUsuarioById(p.getUsuarioId());
            log.info("Usuario encontrado: {}", usuario.getNombre());

        } catch (FeignException.NotFound e) {
            log.warn("Recurso no encontrado (404): {}", e.getMessage());
            throw new RecursoNoEncontradoException("Libro o usuario no encontrado");

        } catch (FeignException e) {
            log.error("Error al llamar servicio externo: status={} mensaje={}", e.status(), e.getMessage());
            throw new ServicioNoDisponibleException("Servicio externo no disponible");
        }

        return new PrestamoDTO(
                p.getId(),
                libro.getTitulo(),
                usuario.getNombre(),
                p.getFechaPrestamo(),
                p.getFechaDevolucion(),
                p.getEstado()
        );
    }

    // POST
    public PrestamoDTO crearPrestamo(PrestamoCreateDTO dto) {

        log.info("Intentando crear préstamo");

        LibroDTO libro;
        UsuarioDTO usuario;

        try {
            log.info("Buscando libro con id={}", dto.getLibroId());
            libro = libroClient.getLibroById(dto.getLibroId());
            log.info("Libro encontrado: {}", libro.getTitulo());

            log.info("Buscando usuario con id={}", dto.getUsuarioId());
            usuario = usuarioClient.getUsuarioById(dto.getUsuarioId());
            log.info("Usuario encontrado: {}", usuario.getNombre());

        } catch (FeignException.NotFound e) {
            log.warn("Recurso no encontrado (404): {}", e.getMessage());
            throw new RecursoNoEncontradoException("Libro o usuario no encontrado");

        } catch (FeignException e) {
            log.error("Error al llamar servicio externo: status={} mensaje={}", e.status(), e.getMessage());
            throw new ServicioNoDisponibleException("Servicio externo no disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(dto.getLibroId());
        prestamo.setUsuarioId(dto.getUsuarioId());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setEstado(dto.getEstado());

        Prestamo guardado = repository.save(prestamo);
        log.info("Préstamo creado correctamente. ID={}", guardado.getId());

        return new PrestamoDTO(
                guardado.getId(),
                libro.getTitulo(),
                usuario.getNombre(),
                guardado.getFechaPrestamo(),
                guardado.getFechaDevolucion(),
                guardado.getEstado()
        );
    }

    // PUT
    public PrestamoDTO actualizarPrestamo(Long id, PrestamoCreateDTO dto) {

        Prestamo prestamo = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Préstamo id={} no encontrado para actualizar", id);
                    return new RecursoNoEncontradoException("Préstamo no encontrado");
                });

        LibroDTO libro;
        UsuarioDTO usuario;

        try {
            log.info("Buscando libro con id={}", dto.getLibroId());
            libro = libroClient.getLibroById(dto.getLibroId());
            log.info("Libro encontrado: {}", libro.getTitulo());

            log.info("Buscando usuario con id={}", dto.getUsuarioId());
            usuario = usuarioClient.getUsuarioById(dto.getUsuarioId());
            log.info("Usuario encontrado: {}", usuario.getNombre());

        } catch (FeignException.NotFound e) {
            log.warn("Recurso no encontrado (404): {}", e.getMessage());
            throw new RecursoNoEncontradoException("Libro o usuario no encontrado");

        } catch (FeignException e) {
            log.error("Error al llamar servicio externo: status={} mensaje={}", e.status(), e.getMessage());
            throw new ServicioNoDisponibleException("Servicio externo no disponible");
        }

        prestamo.setLibroId(dto.getLibroId());
        prestamo.setUsuarioId(dto.getUsuarioId());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setEstado(dto.getEstado());

        Prestamo actualizado = repository.save(prestamo);
        log.info("Préstamo actualizado correctamente. ID={}", actualizado.getId());

        return new PrestamoDTO(
                actualizado.getId(),
                libro.getTitulo(),
                usuario.getNombre(),
                actualizado.getFechaPrestamo(),
                actualizado.getFechaDevolucion(),
                actualizado.getEstado()
        );
    }

    // DELETE
    public void eliminarPrestamo(Long id) {

        Prestamo prestamo = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Préstamo id={} no encontrado para eliminar", id);
                    return new RecursoNoEncontradoException("Préstamo no encontrado");
                });

        repository.delete(prestamo);
        log.info("Préstamo eliminado correctamente. ID={}", id);
    }
}