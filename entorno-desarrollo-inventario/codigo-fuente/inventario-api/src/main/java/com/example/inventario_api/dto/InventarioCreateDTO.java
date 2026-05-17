package com.example.inventario_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioCreateDTO {

    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;

    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    @NotBlank(message = "Ingrese una ubicación")
    private String ubicacion;

    @NotBlank(message = "Ingrese estado activo o inactivo")
    private String estado;
}