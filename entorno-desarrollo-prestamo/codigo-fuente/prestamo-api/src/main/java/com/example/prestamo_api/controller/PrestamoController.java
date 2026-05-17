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
}