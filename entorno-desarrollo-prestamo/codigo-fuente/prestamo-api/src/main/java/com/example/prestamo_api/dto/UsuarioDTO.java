package com.example.prestamo_api.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria")
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

}