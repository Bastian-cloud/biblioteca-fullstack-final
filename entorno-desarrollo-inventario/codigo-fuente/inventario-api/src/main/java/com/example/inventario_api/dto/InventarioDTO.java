package com.example.inventario_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
    private Long id;
    private Long libroId;
    private Integer stock;
    private String ubicacion;
    private String estado;
    
    // OJO: no se incluye passwordHash, ni createdAt, ni relaciones JPA
}