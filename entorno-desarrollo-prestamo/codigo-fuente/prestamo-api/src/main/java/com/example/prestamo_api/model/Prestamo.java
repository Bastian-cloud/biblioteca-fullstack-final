package com.example.prestamo_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libro;
    private String usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;

    public Prestamo(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getLibro(){ return libro; }
    public void setLibro(String libro){ this.libro=libro; }

    public String getUsuario(){ return usuario; }
    public void setUsuario(String usuario){ this.usuario=usuario; }

    public LocalDate getFechaPrestamo(){ return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo){ this.fechaPrestamo=fechaPrestamo; }

    public LocalDate getFechaDevolucion(){ return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion){ this.fechaDevolucion=fechaDevolucion; }

    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado=estado; }
}