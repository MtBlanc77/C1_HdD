package cl.usm.c1_hdd.repositories;

import cl.usm.c1_hdd.entities.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LibrosRepository extends MongoRepository<Libro, String> {
    List<Libro> findByAutor(String autor);
}