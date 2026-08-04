package com.example.crud_libreria.Repository;
import com.example.crud_libreria.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    Optional<Libro> findByCodigoLibro(String codigoLibro);

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutorContainingIgnoreCase(String autor);

    List<Libro> findByGeneroContainingIgnoreCase(String genero);

}
