package com.example.inventario_api.controller;

import com.example.inventario_api.dto.InventarioCreateDTO;
import com.example.inventario_api.dto.InventarioDTO;
import com.example.inventario_api.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Inventario", description = "Operaciones de gestión de inventario y stock de libros")
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    // GET
    @Operation(summary = "Buscar inventario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getInventario(
            @Parameter(description = "ID único del inventario", required = true)
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST
    @Operation(summary = "Crear nuevo registro de inventario")
    @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente")
    @PostMapping
    public ResponseEntity<InventarioDTO> crearInventario(
            @Valid @RequestBody InventarioCreateDTO dto) {

        InventarioDTO creado = service.crearInventario(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT
    @Operation(summary = "Actualizar registro de inventario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(
            @Parameter(description = "ID del inventario a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody InventarioCreateDTO dto) {

        InventarioDTO actualizado = service.actualizarInventario(id, dto);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @Operation(summary = "Eliminar registro de inventario")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Inventario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(
            @Parameter(description = "ID del inventario a eliminar", required = true)
            @PathVariable Long id) {

        service.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}