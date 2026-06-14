package com.example.usuario_api.service;

import com.example.usuario_api.exception.RecursoNoEncontradoException;
import com.example.usuario_api.model.Usuario;
import com.example.usuario_api.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log =
            LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // GET ALL
    public List<Usuario> listar() {

        log.info("Listando todos los usuarios");

        return repo.findAll();
    }

    // GET BY ID
    public Usuario buscar(Long id) {

        log.info("Buscando usuario con id={}", id);

        return repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario id={} no encontrado", id);
                    return new RecursoNoEncontradoException(
                            "Usuario no encontrado");
                });
    }

    // POST
    public Usuario guardar(Usuario usuario) {

        log.info("Creando usuario con correo={}",
                usuario.getCorreo());

        Usuario guardado = repo.save(usuario);

        log.info("Usuario creado correctamente. ID={}",
                guardado.getId());

        return guardado;
    }

    // PUT
    public Usuario actualizar(Long id, Usuario datos) {

        log.info("Actualizando usuario id={}", id);

        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario id={} no encontrado para actualizar", id);
                    return new RecursoNoEncontradoException(
                            "Usuario no encontrado");
                });

        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setRol(datos.getRol());

        Usuario actualizado = repo.save(usuario);

        log.info("Usuario actualizado correctamente. ID={}",
                actualizado.getId());

        return actualizado;
    }

    // DELETE
    public void eliminar(Long id) {

        log.info("Eliminando usuario id={}", id);

        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario id={} no encontrado para eliminar", id);
                    return new RecursoNoEncontradoException(
                            "Usuario no encontrado");
                });

        repo.delete(usuario);

        log.info("Usuario eliminado correctamente. ID={}", id);
    }
}