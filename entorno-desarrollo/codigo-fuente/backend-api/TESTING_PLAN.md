## Pruebas Unitarias y Cobertura de Reglas de Negocio

### Reglas de Negocio Críticas del Servicio de Libros

1. **Búsqueda de Libro:** No se puede obtener un libro que no existe en la base de datos.
2. **Creación de Libro:** Si ocurre un error interno al guardar, se lanza una excepción controlada.
3. **Actualización de Libro:** No se puede actualizar un libro que no existe en la base de datos.
4. **Eliminación de Libro:** No se puede eliminar un libro que no existe en la base de datos.


### Cobertura Actual

| Regla                        | Estado           | Casos Cubiertos                                                        |
|------------------------------|------------------|------------------------------------------------------------------------|
| 1. Búsqueda de Libro         | ✅ Cubierta      | Libro encontrado (caso feliz), Libro no encontrado (caso error)        |
| 2. Creación de Libro         | ⚠️ Pendiente     | Solo caso feliz (libro guardado correctamente)                         |
| 3. Actualización de Libro    | ✅ Cubierta      | Libro actualizado (caso feliz), Libro no encontrado (caso error)       |
| 4. Eliminación de Libro      | ✅ Cubierta      | Libro eliminado (caso feliz), Libro no encontrado (caso error)         |


### Reflexión y Deuda Técnica

- **Riesgo sin probar:** La regla de creación no tiene test para el caso de error interno (excepción del repositorio al hacer `save`).
- **Acción Futura:** Agregar test que simule un fallo en `repository.save()` y verifique que se lanza `RuntimeException` con el mensaje correcto.
- **Responsable:** Equipo Backend · Sprint 4