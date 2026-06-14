package com.example.prestamo_api.dto;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long id;
    private String libro;
    private String usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;

    public PrestamoDTO() {
    }

    public PrestamoDTO(
            Long id,
            String libro,
            String usuario,
            LocalDate fechaPrestamo,
            LocalDate fechaDevolucion,
            String estado) {

        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getLibro() {
        return libro;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}