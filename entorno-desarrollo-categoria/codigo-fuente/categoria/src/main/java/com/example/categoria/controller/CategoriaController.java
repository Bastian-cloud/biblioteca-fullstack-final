package com.example.categoria.controller;

import com.example.categoria.dto.*;
import com.example.categoria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public CategoriaDTO guardar(@Valid @RequestBody CategoriaCreateDTO dto) {
        return service.guardar(dto);
    }
}