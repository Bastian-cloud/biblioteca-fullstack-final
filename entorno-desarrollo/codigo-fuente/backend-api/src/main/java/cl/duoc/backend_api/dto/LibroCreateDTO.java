// src/main/java/cl/duoc/backend_api/dto/LibroDTO.java
package cl.duoc.backend_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroCreateDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    
    @NotBlank(message = "El autor es obligatorio")
    private String autor;
    
    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 2, max = 20, message = "El ISBN debe tener máximo 20 caracteres")
    private String isbn;
   
}