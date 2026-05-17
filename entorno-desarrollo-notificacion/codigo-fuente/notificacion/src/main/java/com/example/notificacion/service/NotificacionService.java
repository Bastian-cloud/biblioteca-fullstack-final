package com.example.notificacion.service;

import com.example.notificacion.dto.*;
import com.example.notificacion.model.Notificacion;
import com.example.notificacion.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<NotificacionDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    public NotificacionDTO guardar(NotificacionCreateDTO dto) {

        Notificacion n = new Notificacion();
        n.setMensaje(dto.getMensaje());
        n.setTipo(dto.getTipo());

        return convertirDTO(repository.save(n));
    }

    private NotificacionDTO convertirDTO(Notificacion n) {
        return new NotificacionDTO(
                n.getId(),
                n.getMensaje(),
                n.getTipo()
        );
    }
}