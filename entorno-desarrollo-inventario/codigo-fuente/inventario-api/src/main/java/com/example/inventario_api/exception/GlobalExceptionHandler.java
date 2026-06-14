package com.example.inventario_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(
            RecursoNoEncontradoException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("status", 404);
        error.put("mensaje", ex.getMessage());
        error.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(
            MethodArgumentNotValidException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("mensaje", "Error de validación");
        error.put("timestamp", LocalDateTime.now());

        Map<String, String> detalles = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->
                detalles.put(err.getField(), err.getDefaultMessage())
        );

        error.put("errores", detalles);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErrorGeneral(
            Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("mensaje", ex.getMessage());
        error.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}