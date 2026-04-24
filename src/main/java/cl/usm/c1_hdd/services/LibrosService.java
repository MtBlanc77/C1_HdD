package cl.usm.c1_hdd.services;

import cl.usm.c1_hdd.entities.Libro;

import java.util.List;

public interface LibrosService {
    Libro createLibro(Libro libro);
    List<Libro> getAll();
    List<Libro> search(String search);
    List<Libro> findByAutor(String autor);
}