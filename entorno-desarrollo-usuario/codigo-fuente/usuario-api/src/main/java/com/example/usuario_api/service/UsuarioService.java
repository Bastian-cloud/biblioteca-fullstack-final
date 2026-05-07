package com.example.usuario_api.service;

import com.example.usuario_api.model.Usuario;
import com.example.usuario_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Optional<Usuario> buscar(Long id) {
        return repo.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        return repo.findById(id).map(usuario -> {
            usuario.setNombre(datos.getNombre());
            usuario.setCorreo(datos.getCorreo());
            usuario.setRol(datos.getRol());
            return repo.save(usuario);
        }).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}