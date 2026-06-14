package com.example.prestamo_api.controller;

import com.example.prestamo_api.dto.PrestamoCreateDTO;
import com.example.prestamo_api.dto.PrestamoDTO;
import com.example.prestamo_api.service.PrestamoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    // GET /api/prestamos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> getPrestamo(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/prestamos
    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(
            @Valid @RequestBody PrestamoCreateDTO dto) {

        PrestamoDTO creado = service.crearPrestamo(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT /api/prestamos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PrestamoDTO> actualizarPrestamo(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoCreateDTO dto) {

        PrestamoDTO actualizado = service.actualizarPrestamo(id, dto);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/prestamos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {

        service.eliminarPrestamo(id);

        return ResponseEntity.noContent().build();
    }
}