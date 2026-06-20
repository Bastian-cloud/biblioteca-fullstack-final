package com.example.prestamo_api.dto;

public class InventarioDTO {

    private Long id;
    private Long libroId;
    private Integer stock;
    private String ubicacion;
    private String estado;

    public InventarioDTO() {}

    public InventarioDTO(
            Long id,
            Long libroId,
            Integer stock,
            String ubicacion,
            String estado) {
        this.id = id;
        this.libroId = libroId;
        this.stock = stock;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getLibroId() { return libroId; }
    public Integer getStock() { return stock; }
    public String getUbicacion() { return ubicacion; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setLibroId(Long libroId) { this.libroId = libroId; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setEstado(String estado) { this.estado = estado; }
}