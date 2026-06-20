package com.example.multa.client;

import com.example.multa.dto.PrestamoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "prestamo-api", url = "${prestamo.service.url}")

public interface PrestamoClient {

    @GetMapping("/api/prestamos/{id}")
    PrestamoDTO getPrestamoById(@PathVariable("id") Long id);
}