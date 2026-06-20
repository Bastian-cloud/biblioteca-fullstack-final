package com.example.prestamo_api.client;

import com.example.prestamo_api.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventario-service", url = "http://entorno-desarrollo-inventario:8081")
public interface InventarioClient {

    @GetMapping("/api/inventario/{id}")
    InventarioDTO getInventarioById(@PathVariable Long id);
}