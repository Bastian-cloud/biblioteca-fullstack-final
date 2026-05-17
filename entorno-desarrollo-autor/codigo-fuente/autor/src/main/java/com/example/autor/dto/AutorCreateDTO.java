package com.example.autor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;
}