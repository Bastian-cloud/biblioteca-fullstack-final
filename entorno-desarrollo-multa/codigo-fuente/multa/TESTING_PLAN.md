## Pruebas Unitarias y Cobertura de Reglas de Negocio

### Reglas de Negocio Críticas del Servicio de Multas

1. **Validación de Préstamo:** No se puede crear ni actualizar una multa si el préstamo asociado no existe (servicio externo devuelve 404).
2. **Disponibilidad del Servicio de Préstamos:** Si el servicio de préstamos no está disponible, se lanza una excepción de servicio no disponible.
3. **Búsqueda de Multa:** No se puede obtener, actualizar ni eliminar una multa que no existe en la base de datos.


### Cobertura Actual

| Regla                          | Estado      | Casos Cubiertos                                                                 |
|--------------------------------|-------------|---------------------------------------------------------------------------------|
| 1. Validación de Préstamo      | ✅ Cubierta | Préstamo encontrado (caso feliz), Préstamo no encontrado - 404 (caso error)     |
| 2. Disponibilidad de Préstamos | ⚠️ Pendiente| Solo caso feliz (servicio disponible)                                           |
| 3. Búsqueda de Multa           | ✅ Cubierta | Multa encontrada (caso feliz), Multa no encontrada (caso error)                 |


### Reflexión y Deuda Técnica

- **Riesgo sin probar:** La regla de disponibilidad del servicio de préstamos no tiene test para el caso en que el servicio esté caído (FeignException genérico, timeout o conexión rechazada).
- **Acción Futura:** Agregar test que simule un `FeignException` genérico en `obtenerPrestamo()` y verifique que se lanza `ServicioNoDisponibleException` correctamente, tanto en creación como en actualización de multas.
- **Responsable:** Equipo Backend · Sprint 4