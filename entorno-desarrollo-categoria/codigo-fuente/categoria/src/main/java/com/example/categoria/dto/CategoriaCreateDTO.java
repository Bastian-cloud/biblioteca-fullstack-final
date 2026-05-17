package com.example.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
}