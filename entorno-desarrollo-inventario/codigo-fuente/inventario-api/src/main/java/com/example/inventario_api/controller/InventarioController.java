package com.example.inventario_api.controller;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.service.InventarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getInventario(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST
    @PostMapping
    public ResponseEntity<InventarioDTO> crearInventario(
            @Valid @RequestBody InventarioCreateDTO dto) {

        InventarioDTO creado =
                service.crearInventario(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(
            @PathVariable Long id,
            @Valid @RequestBody InventarioCreateDTO dto) {

        InventarioDTO actualizado =
                service.actualizarInventario(id, dto);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(
            @PathVariable Long id) {

        service.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}