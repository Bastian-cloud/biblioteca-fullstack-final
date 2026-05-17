package com.example.inventario_api.controller;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.service.InventarioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService service;

    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getLibro(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/libros
    @PostMapping
    public ResponseEntity<InventarioDTO> crearLibro(
            @Valid @RequestBody InventarioCreateDTO dto) {

        InventarioDTO creado = service.crearInventario(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }
}