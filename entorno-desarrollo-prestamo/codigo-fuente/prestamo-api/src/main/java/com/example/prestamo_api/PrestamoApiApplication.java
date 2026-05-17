package com.example.prestamo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PrestamoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamoApiApplication.class, args);
    }
}