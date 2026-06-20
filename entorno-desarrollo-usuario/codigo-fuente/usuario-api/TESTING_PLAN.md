## Reglas de Negocio - Usuario API

### Reglas Implementadas

1. **Validación de Usuario Existente:** No se puede buscar, actualizar o eliminar un usuario que no exista en la base de datos.
2. **Validación de Nombre:** No se puede crear ni actualizar un usuario sin nombre.
3. **Validación de Correo:** No se puede crear ni actualizar un usuario sin correo electrónico.
4. **Validación de Rol:** No se puede crear ni actualizar un usuario sin rol asignado.
5. **Persistencia de Datos:** Todo usuario creado o actualizado debe almacenarse correctamente en la base de datos.

---

### Cobertura Actual

| Regla                              | Estado     | Casos Cubiertos                                                     |
| ---------------------------------- | ---------- | ------------------------------------------------------------------- |
| 1. Validación de Usuario Existente | ✅ Cubierta | Usuario encontrado (caso feliz), Usuario no encontrado (caso error) |
| 2. Validación de Nombre            | ✅ Cubierta | Nombre válido (caso feliz), Nombre vacío (caso error)               |
| 3. Validación de Correo            | ✅ Cubierta | Correo válido (caso feliz), Correo vacío (caso error)               |
| 4. Validación de Rol               | ✅ Cubierta | Rol válido (caso feliz), Rol vacío (caso error)                     |
| 5. Persistencia de Datos           | ✅ Cubierta | Guardado correcto, actualización correcta                           |

---

### Reflexión y Deuda Técnica

* **Riesgo sin probar:** Actualmente no se valida si el correo electrónico ya existe en el sistema, lo que podría permitir duplicados.
* **Acción Futura:** Implementar validación de unicidad de correo antes de guardar usuarios.
* **Responsable:** Equipo Backend · Sprint 4
