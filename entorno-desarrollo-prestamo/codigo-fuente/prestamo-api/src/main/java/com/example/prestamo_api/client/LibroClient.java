package com.example.prestamo_api.client;

import com.example.prestamo_api.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "libro", url = "http://entorno-desarrollo:8080")

public interface LibroClient {

    @GetMapping("/api/libros/{id}")
    LibroDTO getLibroById(@PathVariable Long id);
}
