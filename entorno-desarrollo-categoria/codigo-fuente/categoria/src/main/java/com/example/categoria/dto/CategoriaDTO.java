package com.example.categoria.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
}