package com.example.inventario_api.service;

import com.example.inventario_api.model.Inventario;
import com.example.inventario_api.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public Inventario guardar(Inventario inventario) {
        return repository.save(inventario);
    }

    public List<Inventario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Inventario actualizar(Long id, Inventario datos) {
        Optional<Inventario> actual = repository.findById(id);

        if (actual.isPresent()) {
            Inventario inv = actual.get();
            inv.setLibroId(datos.getLibroId());
            inv.setStock(datos.getStock());
            inv.setUbicacion(datos.getUbicacion());
            inv.setEstado(datos.getEstado());

            return repository.save(inv);
        }

        return null;
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}