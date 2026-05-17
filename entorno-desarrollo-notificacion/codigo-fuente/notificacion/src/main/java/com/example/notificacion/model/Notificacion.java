package com.example.notificacion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private String tipo;
}