package com.example.reserva.service;

import com.example.reserva.model.Reserva;
import com.example.reserva.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Optional<Reserva> buscar(Long id) {
        return repository.findById(id);
    }

    public Reserva guardar(Reserva reserva) {
        return repository.save(reserva);
    }

    public Reserva actualizar(Long id, Reserva reservaActualizada) {
        return repository.findById(id)
                .map(reserva -> {
                    reserva.setUsuarioId(reservaActualizada.getUsuarioId());
                    reserva.setLibroId(reservaActualizada.getLibroId());
                    reserva.setFechaReserva(reservaActualizada.getFechaReserva());
                    reserva.setEstado(reservaActualizada.getEstado());
                    return repository.save(reserva);
                }).orElseThrow();
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}