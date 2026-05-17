package com.example.autor.controller;

import com.example.autor.dto.*;
import com.example.autor.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AutorDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AutorDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public AutorDTO guardar(@Valid @RequestBody AutorCreateDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}