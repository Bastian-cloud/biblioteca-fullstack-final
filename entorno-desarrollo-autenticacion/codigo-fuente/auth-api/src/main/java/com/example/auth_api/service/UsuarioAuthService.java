package com.example.auth_api.service;

import com.example.auth_api.model.UsuarioAuth;
import com.example.auth_api.repository.UsuarioAuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAuthService {

    private final UsuarioAuthRepository repo;

    public UsuarioAuthService(UsuarioAuthRepository repo){
        this.repo = repo;
    }

    public List<UsuarioAuth> listar(){
        return repo.findAll();
    }

    public Optional<UsuarioAuth> buscar(Long id){
        return repo.findById(id);
    }

    public UsuarioAuth guardar(UsuarioAuth u){
        return repo.save(u);
    }

    public UsuarioAuth actualizar(Long id, UsuarioAuth datos){
        return repo.findById(id).map(u -> {
            u.setUsername(datos.getUsername());
            u.setPassword(datos.getPassword());
            u.setRol(datos.getRol());
            return repo.save(u);
        }).orElse(null);
    }

    public void eliminar(Long id){
        repo.deleteById(id);
    }

    public boolean login(String username, String password){
        Optional<UsuarioAuth> user = repo.findByUsername(username);

        return user.isPresent() &&
               user.get().getPassword().equals(password);
    }
}