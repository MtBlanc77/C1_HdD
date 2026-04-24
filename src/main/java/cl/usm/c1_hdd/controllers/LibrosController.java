package cl.usm.c1_hdd.controllers;

import cl.usm.c1_hdd.entities.Libro;
import cl.usm.c1_hdd.services.LibrosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LibrosController {

    @Autowired
    private LibrosService librosService;

    @GetMapping("/libros")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String search) {
        try {
            if (search != null && !search.trim().isEmpty()) {
                return ResponseEntity.ok(this.librosService.search(search.trim()));
            }
            return ResponseEntity.ok(this.librosService.getAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/crearLibro")
    public ResponseEntity<?> createLibro(@RequestBody @Valid Libro libro) {
        try {
            Libro creado = this.librosService.createLibro(libro);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (DuplicateKeyException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Ya existe un libro con ese ISBN"));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error interno del servidor"));
        }
    }

    @GetMapping("/libros/{autor}")
    public ResponseEntity<?> findByAutor(@PathVariable String autor) {
        try {
            List<Libro> libros = this.librosService.findByAutor(autor);
            return ResponseEntity.ok(libros);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error interno del servidor"));
        }
    }
}