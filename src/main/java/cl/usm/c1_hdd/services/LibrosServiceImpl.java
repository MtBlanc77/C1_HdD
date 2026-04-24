package cl.usm.c1_hdd.services;

import cl.usm.c1_hdd.entities.Libro;
import cl.usm.c1_hdd.repositories.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosServiceImpl implements LibrosService {

    @Autowired
    private LibrosRepository librosRepository;

    @Override
    public Libro createLibro(Libro libro) {
        return this.librosRepository.insert(libro);
    }

    @Override
    public List<Libro> getAll() {
        return this.librosRepository.findAll();
    }

    @Override
    public List<Libro> search(String search) {
        String query = search.toLowerCase();

        return this.librosRepository.findAll().stream()
                .filter(libro ->
                        (libro.getTitulo() != null && libro.getTitulo().toLowerCase().contains(query)) ||
                                (libro.getAutor() != null && libro.getAutor().toLowerCase().contains(query))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> findByAutor(String autor) {
        return this.librosRepository.findByAutor(autor);
    }
}