package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.service.LibroService;

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

@Tag(name = "Libros", description = "Operaciones de gestión del catálogo de libros")
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService service;

    // GET /api/libros/{id}
    @Operation(summary = "Buscar libro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro encontrado"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getLibro(
            @Parameter(description = "ID único del libro", required = true)
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/libros
    @Operation(summary = "Crear nuevo libro")
    @ApiResponse(responseCode = "201", description = "Libro creado exitosamente")
    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(
            @Valid @RequestBody LibroCreateDTO dto) {

        LibroDTO creado = service.crearLibro(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT /api/libros/{id}
    @Operation(summary = "Actualizar libro existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizarLibro(
            @Parameter(description = "ID del libro a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody LibroCreateDTO dto) {

        LibroDTO actualizado = service.actualizarLibro(id, dto);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/libros/{id}
    @Operation(summary = "Eliminar libro")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(
            @Parameter(description = "ID del libro a eliminar", required = true)
            @PathVariable Long id) {

        service.eliminarLibro(id);

        return ResponseEntity.noContent().build();
    }
}