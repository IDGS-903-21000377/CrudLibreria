package com.example.crud_libreria.Service;
import com.example.crud_libreria.Model.Libro;
import com.example.crud_libreria.Repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public List<Libro> listar() {
        return repository.findAll();
    }

    public Libro guardar(Libro libro) {

        // Si es un libro nuevo genera el código
        if (libro.getCodigoLibro() == null || libro.getCodigoLibro().isBlank()) {
            libro.setCodigoLibro(generarCodigo());
        }

        return repository.save(libro);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private String generarCodigo() {

        Random random = new Random();

        String codigo;

        do {
            char letra = (char) ('A' + random.nextInt(26));
            int numero = random.nextInt(100);
            codigo = letra + String.format("%02d", numero);

            // Evita repetir códigos
        } while (repository.findByCodigoLibro(codigo).isPresent());

        return codigo;
    }

    public List<Libro> buscar(String titulo, String autor, String genero) {

        if (titulo != null && !titulo.isBlank()) {
            return repository.findByTituloContainingIgnoreCase(titulo);
        }

        if (autor != null && !autor.isBlank()) {
            return repository.findByAutorContainingIgnoreCase(autor);
        }

        if (genero != null && !genero.isBlank()) {
            return repository.findByGeneroContainingIgnoreCase(genero);
        }

        return repository.findAll();
    }

    public Libro buscarPorCodigo(String codigo) {
        return repository.findByCodigoLibro(codigo)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public void eliminarPorCodigo(String codigo) {
        Libro libro = buscarPorCodigo(codigo);
        repository.delete(libro);
    }
}

