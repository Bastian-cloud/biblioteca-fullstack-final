package com.example.notificacion.controller;

import com.example.notificacion.dto.*;
import com.example.notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<NotificacionDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public NotificacionDTO guardar(@Valid @RequestBody NotificacionCreateDTO dto) {
        return service.guardar(dto);
    }
}