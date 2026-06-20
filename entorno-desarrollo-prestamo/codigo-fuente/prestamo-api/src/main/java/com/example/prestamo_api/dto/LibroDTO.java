package com.example.prestamo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    private Long LibroId;
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
}