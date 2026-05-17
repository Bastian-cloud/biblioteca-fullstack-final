package com.example.categoria.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;
}