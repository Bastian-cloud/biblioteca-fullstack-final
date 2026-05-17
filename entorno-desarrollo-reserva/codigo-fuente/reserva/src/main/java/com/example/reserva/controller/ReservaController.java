package com.example.reserva.controller;

import com.example.reserva.model.Reserva;
import com.example.reserva.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reserva> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable Long id) {
        return service.buscar(id).orElseThrow();
    }

    @PostMapping
    public Reserva guardar(@RequestBody Reserva reserva) {
        return service.guardar(reserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Long id,
                              @RequestBody Reserva reserva) {
        return service.actualizar(id, reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}