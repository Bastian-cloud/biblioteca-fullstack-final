package com.example.multa.service;

import com.example.multa.client.PrestamoClient;
import com.example.multa.dto.MultaCreateDTO;
import com.example.multa.dto.MultaDTO;
import com.example.multa.dto.PrestamoDTO;
import com.example.multa.exception.RecursoNoEncontradoException;
import com.example.multa.exception.ServicioNoDisponibleException;
import com.example.multa.model.Multa;
import com.example.multa.repository.MultaRepository;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class MultaService {

    private static final Logger log =
            LoggerFactory.getLogger(MultaService.class);

    private final MultaRepository repository;
    private final PrestamoClient prestamoClient;

    @Value("${multa.valor-por-dia:1000}")
    private double valorMultaPorDia;

    public MultaService(
            MultaRepository repository,
            PrestamoClient prestamoClient) {

        this.repository = repository;
        this.prestamoClient = prestamoClient;
    }

    // Método reutilizable para consultar préstamo
    private PrestamoDTO obtenerPrestamo(Long prestamoId) {

        try {

            return prestamoClient.getPrestamoById(prestamoId);

        } catch (FeignException.NotFound e) {

            log.warn("Préstamo id={} no encontrado", prestamoId);

            throw new RecursoNoEncontradoException(
                    "Préstamo no encontrado");

        } catch (FeignException e) {

            log.error("Error consultando servicio Préstamos: {}",
                    e.getMessage());

            throw new ServicioNoDisponibleException(
                    "Servicio de préstamos no disponible");
        }
    }

    // Calcula los días de retraso a partir de la fecha de devolución esperada
    private int calcularDiasRetraso(LocalDate fechaDevolucionEsperada, LocalDate fechaReferencia) {

        long dias = ChronoUnit.DAYS.between(fechaDevolucionEsperada, fechaReferencia);

        return (int) Math.max(dias, 0);
    }

    // GET /api/multas/{id}
    public MultaDTO findDtoById(Long id) {

        log.info("Buscando multa con id={}", id);

        Multa multa = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Multa id={} no encontrada", id);

                    return new RecursoNoEncontradoException(
                            "Multa no encontrada");
                });

        PrestamoDTO prestamo = obtenerPrestamo(
                multa.getPrestamoId()
        );

        return new MultaDTO(
                multa.getId(),
                prestamo.getLibro(),
                prestamo.getUsuario(),
                multa.getMonto(),
                multa.getDiasRetraso(),
                multa.getEstado(),
                multa.getFechaMulta()
        );
    }

    // POST /api/multas
    public MultaDTO crearMulta(MultaCreateDTO dto) {

        log.info("Creando multa para préstamo id={}",
                dto.getPrestamoId());

        PrestamoDTO prestamo = obtenerPrestamo(
                dto.getPrestamoId()
        );

        int diasRetraso = calcularDiasRetraso(
                prestamo.getFechaDevolucion(),
                dto.getFechaMulta()
        );

        double monto = diasRetraso * valorMultaPorDia;

        Multa multa = new Multa();

        multa.setPrestamoId(dto.getPrestamoId());
        multa.setUsuarioId(dto.getUsuarioId());
        multa.setMonto(monto);
        multa.setDiasRetraso(diasRetraso);
        multa.setEstado(dto.getEstado());
        multa.setFechaMulta(dto.getFechaMulta());

        Multa guardada = repository.save(multa);

        log.info("Multa creada correctamente. ID={}, diasRetraso={}, monto={}",
                guardada.getId(), diasRetraso, monto);

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

    // PUT /api/multas/{id}
    public MultaDTO actualizarMulta(Long id, MultaCreateDTO dto) {

        log.info("Actualizando multa con id={}", id);

        Multa multa = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Multa id={} no encontrada", id);

                    return new RecursoNoEncontradoException(
                            "Multa no encontrada");
                });

        PrestamoDTO prestamo = obtenerPrestamo(
                dto.getPrestamoId()
        );

        int diasRetraso = calcularDiasRetraso(
                prestamo.getFechaDevolucion(),
                dto.getFechaMulta()
        );

        double monto = diasRetraso * valorMultaPorDia;

        multa.setPrestamoId(dto.getPrestamoId());
        multa.setUsuarioId(dto.getUsuarioId());
        multa.setMonto(monto);
        multa.setDiasRetraso(diasRetraso);
        multa.setEstado(dto.getEstado());
        multa.setFechaMulta(dto.getFechaMulta());

        Multa actualizada = repository.save(multa);

        log.info("Multa actualizada correctamente. ID={}, diasRetraso={}, monto={}",
                actualizada.getId(), diasRetraso, monto);

        return new MultaDTO(
                actualizada.getId(),
                prestamo.getLibro(),
                prestamo.getUsuario(),
                actualizada.getMonto(),
                actualizada.getDiasRetraso(),
                actualizada.getEstado(),
                actualizada.getFechaMulta()
        );
    }

    // DELETE /api/multas/{id}
    public void eliminarMulta(Long id) {

        log.info("Eliminando multa con id={}", id);

        Multa multa = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Multa id={} no encontrada", id);

                    return new RecursoNoEncontradoException(
                            "Multa no encontrada");
                });

        repository.delete(multa);

        log.info("Multa eliminada correctamente. ID={}", id);
    }
}