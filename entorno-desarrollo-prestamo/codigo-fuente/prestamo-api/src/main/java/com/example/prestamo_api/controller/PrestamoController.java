package com.example.prestamo_api.controller;

import com.example.prestamo_api.dto.PrestamoCreateDTO;
import com.example.prestamo_api.dto.PrestamoDTO;
import com.example.prestamo_api.service.PrestamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Préstamos", description = "Operaciones de gestión de préstamos de la biblioteca")
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    // GET /api/prestamos/{id}
    @Operation(summary = "Buscar préstamo por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> getPrestamo(
            @Parameter(description = "ID único del préstamo", required = true)
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/prestamos
    @Operation(summary = "Crear nuevo préstamo")
    @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente")
    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(
            @Valid @RequestBody PrestamoCreateDTO dto) {

        PrestamoDTO creado = service.crearPrestamo(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT /api/prestamos/{id}
    @Operation(summary = "Actualizar préstamo existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PrestamoDTO> actualizarPrestamo(
            @Parameter(description = "ID del préstamo a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody PrestamoCreateDTO dto) {

        PrestamoDTO actualizado = service.actualizarPrestamo(id, dto);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/prestamos/{id}
    @Operation(summary = "Eliminar préstamo")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Préstamo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(
            @Parameter(description = "ID del préstamo a eliminar", required = true)
            @PathVariable Long id) {

        service.eliminarPrestamo(id);

        return ResponseEntity.noContent().build();
    }
}