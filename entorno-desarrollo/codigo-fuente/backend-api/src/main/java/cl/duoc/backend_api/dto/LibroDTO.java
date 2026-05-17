// src/main/java/cl/duoc/backend_api/dto/LibroDTO.java
package cl.duoc.backend_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    // OJO: no se incluye passwordHash, ni createdAt, ni relaciones JPA
}