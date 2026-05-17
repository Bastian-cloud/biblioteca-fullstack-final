package com.example.prestamo_api.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {

    private Long id;

    private String libro;

    private String usuario;

    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion;

    private String estado;
}