package com.example.multa.controller;

import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.service.MultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Multas", description = "Operaciones de gestión de multas por atraso en devoluciones")
@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaService service;

    public MultaController(MultaService service) {
        this.service = service;
    }

    // GET /api/multas/{id}
    @Operation(summary = "Buscar multa por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Multa encontrada"),
        @ApiResponse(responseCode = "404", description = "Multa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> getMulta(
            @Parameter(description = "ID único de la multa", required = true)
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/multas
    @Operation(summary = "Crear nueva multa")
    @ApiResponse(responseCode = "201", description = "Multa creada exitosamente")
    @PostMapping
    public ResponseEntity<MultaDTO> crearMulta(
            @Valid @RequestBody MultaCreateDTO dto) {

        MultaDTO creada = service.crearMulta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    // PUT /api/multas/{id}
    @Operation(summary = "Actualizar multa existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Multa actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Multa no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MultaDTO> actualizarMulta(
            @Parameter(description = "ID de la multa a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody MultaCreateDTO dto) {

        return ResponseEntity.ok(
                service.actualizarMulta(id, dto)
        );
    }

    // DELETE /api/multas/{id}
    @Operation(summary = "Eliminar multa")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Multa eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Multa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMulta(
            @Parameter(description = "ID de la multa a eliminar", required = true)
            @PathVariable Long id) {

        service.eliminarMulta(id);

        return ResponseEntity.ok(
                "Multa eliminada correctamente"
        );
    }
}