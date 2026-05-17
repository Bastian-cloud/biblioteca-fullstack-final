package com.example.autor.service;

import com.example.autor.dto.*;
import com.example.autor.model.Autor;
import com.example.autor.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<AutorDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    public AutorDTO obtener(Long id) {
        Autor autor = repository.findById(id).orElseThrow();
        return convertirDTO(autor);
    }

    public AutorDTO guardar(AutorCreateDTO dto) {
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());

        return convertirDTO(repository.save(autor));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private AutorDTO convertirDTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getNacionalidad()
        );
    }
}