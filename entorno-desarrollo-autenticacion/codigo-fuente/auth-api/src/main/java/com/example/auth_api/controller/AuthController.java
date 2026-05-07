package com.example.auth_api.controller;

import com.example.auth_api.model.UsuarioAuth;
import com.example.auth_api.service.UsuarioAuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioAuthService service;

    public AuthController(UsuarioAuthService service){
        this.service = service;
    }

    // Registrar usuario
    @PostMapping("/register")
    public UsuarioAuth registrar(@RequestBody UsuarioAuth u){
        return service.guardar(u);
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> datos){

        boolean acceso = service.login(
                datos.get("username"),
                datos.get("password")
        );

        return acceso ? "Login correcto" : "Credenciales inválidas";
    }

    // Listar usuarios
    @GetMapping("/usuarios")
    public List<UsuarioAuth> listar(){
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/usuarios/{id}")
    public UsuarioAuth buscar(@PathVariable Long id){
        return service.buscar(id).orElse(null);
    }

    // Actualizar
    @PutMapping("/usuarios/{id}")
    public UsuarioAuth actualizar(@PathVariable Long id,
                                  @RequestBody UsuarioAuth u){
        return service.actualizar(id,u);
    }

    // Eliminar
    @DeleteMapping("/usuarios/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}