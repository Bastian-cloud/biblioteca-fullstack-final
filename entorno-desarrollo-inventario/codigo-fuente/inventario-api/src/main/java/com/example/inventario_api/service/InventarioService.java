package com.example.inventario_api.service;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.exception.RecursoNoEncontradoException;
import com.example.inventario_api.model.Inventario;
import com.example.inventario_api.repository.InventarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private static final Logger log =
            LoggerFactory.getLogger(InventarioService.class);

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    // GET
    public InventarioDTO findDtoById(Long id) {

        log.info("Buscando inventario con id={}", id);

        Inventario i = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inventario id={} no encontrado", id);
                    return new RecursoNoEncontradoException(
                            "Inventario no encontrado");
                });

        return new InventarioDTO(
                i.getId(),
                i.getLibroId(),
                i.getStock(),
                i.getUbicacion(),
                i.getEstado()
        );
    }

    // POST
    public InventarioDTO crearInventario(InventarioCreateDTO dto) {

        log.info("Creando inventario para libro id={}",
                dto.getLibroId());

        Inventario inventario = new Inventario();
        inventario.setLibroId(dto.getLibroId());
        inventario.setStock(dto.getStock());
        inventario.setUbicacion(dto.getUbicacion());
        inventario.setEstado(dto.getEstado());

        Inventario guardado = repository.save(inventario);

        log.info("Inventario creado correctamente. ID={}",
                guardado.getId());

        return new InventarioDTO(
                guardado.getId(),
                guardado.getLibroId(),
                guardado.getStock(),
                guardado.getUbicacion(),
                guardado.getEstado()
        );
    }

    // PUT
    public InventarioDTO actualizarInventario(Long id,
                                              InventarioCreateDTO dto) {

        log.info("Actualizando inventario id={}", id);

        Inventario inventario = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inventario id={} no encontrado para actualizar", id);
                    return new RecursoNoEncontradoException(
                            "Inventario no encontrado");
                });

        inventario.setLibroId(dto.getLibroId());
        inventario.setStock(dto.getStock());
        inventario.setUbicacion(dto.getUbicacion());
        inventario.setEstado(dto.getEstado());

        Inventario actualizado = repository.save(inventario);

        log.info("Inventario actualizado correctamente. ID={}",
                actualizado.getId());

        return new InventarioDTO(
                actualizado.getId(),
                actualizado.getLibroId(),
                actualizado.getStock(),
                actualizado.getUbicacion(),
                actualizado.getEstado()
        );
    }

    // DELETE
    public void eliminarInventario(Long id) {

        log.info("Eliminando inventario id={}", id);

        Inventario inventario = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inventario id={} no encontrado para eliminar", id);
                    return new RecursoNoEncontradoException(
                            "Inventario no encontrado");
                });

        repository.delete(inventario);

        log.info("Inventario eliminado correctamente. ID={}", id);
    }
}