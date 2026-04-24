package cl.usm.c1_hdd.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "libros")
public class Libro {

    @Id
    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 13, max = 13, message = "El ISBN debe tener largo 13")
    private String isbn;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @Min(value = 11, message = "Las paginas deben ser mayores que 10")
    private int paginas;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;
}