package com.example.prestamo_api.service;

import com.example.prestamo_api.model.Prestamo;
import com.example.prestamo_api.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository repo;

    public PrestamoService(PrestamoRepository repo){
        this.repo = repo;
    }

    public List<Prestamo> listar(){
        return repo.findAll();
    }

    public Optional<Prestamo> buscar(Long id){
        return repo.findById(id);
    }

    public Prestamo guardar(Prestamo p){
        return repo.save(p);
    }

    public Prestamo actualizar(Long id, Prestamo datos){
        return repo.findById(id).map(p -> {
            p.setLibro(datos.getLibro());
            p.setUsuario(datos.getUsuario());
            p.setFechaPrestamo(datos.getFechaPrestamo());
            p.setFechaDevolucion(datos.getFechaDevolucion());
            p.setEstado(datos.getEstado());
            return repo.save(p);
        }).orElse(null);
    }

    public void eliminar(Long id){
        repo.deleteById(id);
    }
}