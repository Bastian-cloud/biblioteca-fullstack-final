package com.example.usuario_api.controller;

import com.example.usuario_api.model.Usuario;
import com.example.usuario_api.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscar(id)
        );
    }

    // POST
    @PostMapping
    public ResponseEntity<Usuario> guardar(
            @Valid @RequestBody Usuario usuario) {

        Usuario creado = service.guardar(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuario) {

        Usuario actualizado =
                service.actualizar(id, usuario);

        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}