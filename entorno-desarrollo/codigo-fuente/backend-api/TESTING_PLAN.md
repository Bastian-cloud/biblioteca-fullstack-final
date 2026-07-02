# Testing Plan — backend-api (Libro-Service)

## Reglas de Negocio Críticas

1. **Existencia de Libro:** No se puede obtener, actualizar ni eliminar un libro que no existe en la base de datos.
2. **Creación de Libro:** Todo nuevo libro debe quedar correctamente registrado con título, autor e ISBN.
3. **Persistencia de Datos:** Los libros deben guardarse y recuperarse correctamente desde la base de datos, respetando la integridad de sus campos.
4. **Integridad del Modelo:** La entidad Libro debe exponer correctamente sus atributos (getters/setters) y garantizar que un nuevo objeto no tenga un ID asignado hasta ser persistido.

---

### Cobertura Actual

| Regla                              | Estado       | Casos Cubiertos                                                              |
|-------------------------------------|---------------|----------------------------------------------------------------------|
| 1. Existencia de Libro               | ✅ Cubierta  | Libro encontrado (caso feliz - Controller/Service), Libro no encontrado - excepción (caso error - Service) |
| 2. Creación de Libro                 | ✅ Cubierta  | Creación exitosa vía Controller (caso feliz)                                  |
| 3. Persistencia de Datos             | ✅ Cubierta  | Guardado correcto en base de datos (caso feliz - Repository), búsqueda inexistente retorna vacío (caso error - Repository) |
| 4. Integridad del Modelo             | ✅ Cubierta  | Asignación y recuperación de atributos (caso feliz - Model), ID nulo antes de persistir (caso error - Model) |

---

### Reflexión y Deuda Técnica

- **Riesgo sin probar:** No existe test para el caso de actualización (`PUT`) ni eliminación (`DELETE`) de un libro inexistente a nivel de Controller — solo se cubrió a nivel de Service. Tampoco se probó el caso de validación fallida (`@Valid`) al crear un libro con datos incompletos (título, autor o ISBN vacíos/nulos), ni el manejo de la excepción genérica (`RuntimeException`) que el Service lanza ante errores inesperados de persistencia.
- **Acción Futura:** Agregar tests de Controller para `PUT` y `DELETE` sobre libros inexistentes (esperando 404), un test de validación de `LibroCreateDTO` con campos nulos/vacíos para confirmar que Spring rechaza la solicitud con 400, y un test que simule una excepción del repositorio durante `crearLibro`/`actualizarLibro`/`eliminarLibro` para verificar el manejo de errores internos.
- **Responsable:** Bastian Mendoza