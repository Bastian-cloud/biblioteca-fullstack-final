package com.example.multa.controller;

import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.service.MultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaService service;

    public MultaController(MultaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> getMulta(@PathVariable Long id) {
        return ResponseEntity.ok(service.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<MultaDTO> crearMulta(@Valid @RequestBody MultaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearMulta(dto));
    }
}