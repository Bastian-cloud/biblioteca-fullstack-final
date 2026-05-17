package com.example.prestamo_api.client;

import com.example.prestamo_api.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "usuario", url = "http://entorno-desarrollo-usuario:8083")

public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO getUsuarioById(@PathVariable Long id);

} 

