package com.example.inventario_api.service;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.model.Inventario;
import com.example.inventario_api.repository.InventarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repository;

    // GET /api/libros/{id}
    public InventarioDTO findDtoById(Long id) {

        Inventario i = repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Libro no encontrado"));

        return new InventarioDTO(
            i.getId(),
            i.getLibroId(),
            i.getStock(),
            i.getUbicacion(),
            i.getEstado()
        );
 
    }

    // POST /api/libros
    public InventarioDTO crearInventario(InventarioCreateDTO dto) {

        // 1. Convertir DTO de entrada a entidad
        Inventario libro = new Inventario();
        libro.setLibroId(dto.getLibroId());
        libro.setStock(dto.getStock());
        libro.setUbicacion(dto.getUbicacion());
        libro.setEstado(dto.getEstado());

        // 2. Guardar en BD
        Inventario guardado = repository.save(libro);

        // 3. Convertir entidad guardada a DTO de salida
        return new InventarioDTO(
            guardado.getId(),
            guardado.getLibroId(),
            guardado.getStock(),
            guardado.getUbicacion(),
            guardado.getEstado()
        );
    }
}