package com.example.prestamo_api.controller;

import com.example.prestamo_api.model.Prestamo;
import com.example.prestamo_api.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service){
        this.service = service;
    }

    @GetMapping
    public List<Prestamo> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Prestamo buscar(@PathVariable Long id){
        return service.buscar(id).orElse(null);
    }

    @PostMapping
    public Prestamo guardar(@RequestBody Prestamo p){
        return service.guardar(p);
    }

    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Long id,@RequestBody Prestamo p){
        return service.actualizar(id,p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}