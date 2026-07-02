package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.LibroCreateDTO;
import cl.duoc.backend_api.dto.LibroDTO;
import cl.duoc.backend_api.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getLibro_deberiaRetornar200ConLibroExistente() throws Exception {
        LibroDTO dto = new LibroDTO(1L, "Clean Code", "Robert C. Martin", "978-0132350884");

        when(service.findDtoById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/libros/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Clean Code"));
    }

    @Test
    void crearLibro_deberiaRetornar201ConLibroCreado() throws Exception {
        LibroCreateDTO createDto = new LibroCreateDTO();
        createDto.setTitulo("Clean Code");
        createDto.setAutor("Robert C. Martin");
        createDto.setIsbn("978-0132350884");

        LibroDTO respuesta = new LibroDTO(1L, "Clean Code", "Robert C. Martin", "978-0132350884");

        when(service.crearLibro(any(LibroCreateDTO.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/libros")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.autor").value("Robert C. Martin"));
    }
}