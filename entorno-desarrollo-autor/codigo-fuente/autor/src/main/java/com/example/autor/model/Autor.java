package com.example.autor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nacionalidad;
}