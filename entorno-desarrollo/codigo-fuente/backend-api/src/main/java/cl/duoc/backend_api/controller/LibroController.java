package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.service.LibroService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService service;

    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getLibro(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.findDtoById(id)
        );
    }

    // POST /api/libros
    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(
            @Valid @RequestBody LibroCreateDTO dto) {

        LibroDTO creado = service.crearLibro(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }
}