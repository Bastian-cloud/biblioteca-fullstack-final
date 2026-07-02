# Testing Plan — inventario-api

## Reglas de Negocio Críticas

1. **Existencia de Inventario:** No se puede obtener, actualizar ni eliminar un registro de inventario que no existe en la base de datos.
2. **Creación de Inventario:** Todo nuevo registro de inventario debe quedar correctamente asociado a un libro (libroId), con stock, ubicación y estado definidos.
3. **Persistencia de Datos:** Los registros de inventario deben guardarse y recuperarse correctamente desde la base de datos, respetando la integridad de sus campos.
4. **Integridad del Modelo:** La entidad Inventario debe exponer correctamente sus atributos (getters/setters) y garantizar que un nuevo objeto no tenga un ID asignado hasta ser persistido.

---

### Cobertura Actual

| Regla                              | Estado       | Casos Cubiertos                                                              |
|-------------------------------------|--------------|-------------------------------------------------------------------------------|
| 1. Existencia de Inventario         | ✅ Cubierta  | Inventario encontrado (caso feliz - Controller/Service), Inventario no encontrado - excepción (caso error - Service) |
| 2. Creación de Inventario           | ✅ Cubierta  | Creación exitosa vía Controller (caso feliz)                                  |
| 3. Persistencia de Datos            | ✅ Cubierta  | Guardado correcto en base de datos (caso feliz - Repository), búsqueda inexistente retorna vacío (caso error - Repository) |
| 4. Integridad del Modelo            | ✅ Cubierta  | Asignación y recuperación de atributos (caso feliz - Model), ID nulo antes de persistir (caso error - Model) |

---

### Reflexión y Deuda Técnica

- **Riesgo sin probar:** No existe test para el caso de actualización (`PUT`) ni eliminación (`DELETE`) de un inventario inexistente a nivel de Controller — solo se cubrió a nivel de Service. Tampoco se probó el caso de validación fallida (`@NotNull`, `@NotBlank`) al crear un inventario con datos incompletos.
- **Acción Futura:** Agregar tests de Controller para `PUT` y `DELETE` sobre inventarios inexistentes (esperando 404), y un test de validación de `InventarioCreateDTO` con campos nulos/vacíos para confirmar que Spring rechaza la solicitud con 400.
- **Responsable:** Bastian Mendoza