package com.example.notificacion.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {

    private Long id;
    private String mensaje;
    private String tipo;
}