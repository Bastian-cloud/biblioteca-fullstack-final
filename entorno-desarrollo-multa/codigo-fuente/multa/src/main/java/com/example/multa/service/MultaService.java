package com.example.multa.service;

import com.example.multa.client.PrestamoClient;
import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.dto.PrestamoDTO;
import com.example.multa.model.Multa;
import com.example.multa.repository.MultaRepository;
import org.springframework.stereotype.Service;

@Service
public class MultaService {

    private final MultaRepository repository;
    private final PrestamoClient prestamoClient;

    public MultaService(MultaRepository repository, PrestamoClient prestamoClient) {
        this.repository = repository;
        this.prestamoClient = prestamoClient;
    }

    // GET /api/multas/{id}
    public MultaDTO findDtoById(Long id) {
        Multa m = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada"));

        PrestamoDTO prestamo = prestamoClient.getPrestamoById(m.getPrestamoId());

        return new MultaDTO(
                m.getId(),
                prestamo.getLibro(),
                prestamo.getUsuario(),
                m.getMonto(),
                m.getDiasRetraso(),
                m.getEstado(),
                m.getFechaMulta()
        );
    }

    // POST /api/multas
    public MultaDTO crearMulta(MultaCreateDTO dto) {
        PrestamoDTO prestamo = prestamoClient.getPrestamoById(dto.getPrestamoId());

        if (prestamo == null) {
            throw new RuntimeException("Préstamo no encontrado");
        }

        Multa multa = new Multa();
        multa.setPrestamoId(dto.getPrestamoId());
        multa.setUsuarioId(dto.getUsuarioId());
        multa.setMonto(dto.getMonto());
        multa.setDiasRetraso(dto.getDiasRetraso());
        multa.setEstado(dto.getEstado());
        multa.setFechaMulta(dto.getFechaMulta());

        Multa guardada = repository.save(multa);

        return new MultaDTO(
                guardada.getId(),
                prestamo.getLibro(),
                prestamo.getUsuario(),
                guardada.getMonto(),
                guardada.getDiasRetraso(),
                guardada.getEstado(),
                guardada.getFechaMulta()
        );
    }
}