package com.example.categoria.service;

import com.example.categoria.dto.*;
import com.example.categoria.model.Categoria;
import com.example.categoria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO guardar(CategoriaCreateDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        return convertirDTO(repository.save(categoria));
    }

    private CategoriaDTO convertirDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}