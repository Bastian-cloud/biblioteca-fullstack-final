package com.example.multa.dto;

import java.time.LocalDate;

public class MultaDTO {

    private Long id;
    private String prestamo;   // título del libro desde prestamo-api
    private String usuario;    // nombre del usuario desde prestamo-api
    private Double monto;
    private Integer diasRetraso;
    private String estado;
    private LocalDate fechaMulta;

    public MultaDTO() {}

    public MultaDTO(Long id, String prestamo, String usuario, Double monto,
                    Integer diasRetraso, String estado, LocalDate fechaMulta) {
        this.id = id;
        this.prestamo = prestamo;
        this.usuario = usuario;
        this.monto = monto;
        this.diasRetraso = diasRetraso;
        this.estado = estado;
        this.fechaMulta = fechaMulta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrestamo() { return prestamo; }
    public void setPrestamo(String prestamo) { this.prestamo = prestamo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public Integer getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(Integer diasRetraso) { this.diasRetraso = diasRetraso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaMulta() { return fechaMulta; }
    public void setFechaMulta(LocalDate fechaMulta) { this.fechaMulta = fechaMulta; }
}