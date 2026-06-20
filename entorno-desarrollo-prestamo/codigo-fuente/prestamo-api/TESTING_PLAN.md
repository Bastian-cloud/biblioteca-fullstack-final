1. **Validación de Libro:** No se puede crear un préstamo si el libro no existe en el sistema (servicio externo devuelve 404).
2. **Validación de Usuario:** No se puede crear un préstamo si el usuario no existe en el sistema (servicio externo devuelve 404).
3. **Validación de Stock en inventario** No se podría crear un préstamo si el libro que se solicita no tiene stock 
4. **Disponibilidad de Servicios Externos:** Si el servicio de libros o usuarios no está disponible, se lanza una excepción de servicio no disponible.
5. **Búsqueda de Préstamo:** No se puede obtener, actualizar ni eliminar un préstamo que no existe en la base de datos.


---

### Cobertura Actual

| Regla                                   | Estado        | Casos Cubiertos                                                         |
|-----------------------------------------|---------------|-------------------------------------------------------------------------|
| 1. Validación de Libro                  | ✅ Cubierta  |Libro encontrado (caso feliz), Libro no encontrado - 404 (caso error)    
| 2. Validación de Usuario                | ✅ Cubierta  |Usuario encontrado (caso feliz), Usuario no encontrado - 404 (caso error)
| 3. Validación de Stock en inventario    | ✅ Cubierta  |Stock encontrado (Caso feliz), Stock no encontrado - 404 (Caso error)
| 4. Disponibilidad de  Servicios Externos| ✅ Cubierta  |Solo caso feliz (servicios disponibles)                                  
| 5. Búsqueda de Préstamo                 | ✅ Cubierta  |Préstamo encontrado (caso feliz), Préstamo no encontrado (caso error)    


### Reflexión y Deuda Técnica

- **Riesgo sin probar:** La regla de disponibilidad de servicios externos no tiene test para el caso en que el servicio de libros o usuarios esté caído (timeout, conexión rechazada).
- **Acción Futura:** Agregar test para simular fallo total del servicio externo (FeignException genérico) tanto en creación como en actualización de préstamos.
- **Responsable:** Bastian Mendoza